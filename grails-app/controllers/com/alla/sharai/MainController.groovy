package com.alla.sharai

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.plugins.rendering.pdf.PdfRenderingService
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND

class MainController {

    def springSecurityService
    DocumentService documentService
    PdfRenderingService pdfRenderingService
    UserService userService
    def mailService

    static allowedMethods = [saveDocument: "POST", updateDocumentsStatus: "POST", registerUser: "POST"]

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index() {}

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def register() {
        respond new User(params)
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    @Transactional
    def registerUser(User user) {
        if (user == null) {
            notFound()
            return
        }

        def newUser = new User(username: user.username, enabled: true, accountLocked: true,
                password: springSecurityService.encodePassword(user.password),
                pesel: user.pesel, email: user.email,
                firstName: user.firstName, lastName: user.lastName)

        try {
            userService.save(newUser)
            UserRole.create(newUser, Role.findByAuthority("ROLE_USER"), true)
            def registrationToken = new RegistrationToken(token: UUID.randomUUID().toString(), owner: newUser)
            registrationToken.save(flash: true)

            mailService.sendMail {
                to newUser.email
                from "admin@eSystem.com"
                subject "New user"
                text "Please open the link to activate your account \n 127.0.0.1:9090/main/activate?token=" + registrationToken.token
            }

        } catch (ValidationException e) {
            respond user.errors, view: 'register'
            return
        }
        redirect(controller: "main", action: "index")
    }

    @Transactional
    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def activate(String token) {
        RegistrationToken registrationToken = RegistrationToken.findByToken(token)
        User user = registrationToken.getOwner()
        user.accountLocked = false
        userService.save(user)
        redirect(controller: "main", action: "index")
    }

    @Secured(['ROLE_USER'])
    def userPage() {
        User currentUser = getCurrentUser()
        List<Document> documentList = Document.findAllByOwner(currentUser)
        respond documentList, model: [documentCount: documentList.size()]
    }

    @Secured(['ROLE_USER'])
    def createDocument() {
        respond new Document(params)
    }

    @Secured(['ROLE_USER'])
    def saveDocument(Document document) {

        if (document == null) {
            return
        }

        User currentUser = getCurrentUser()
        document.approved = false
        document.owner = currentUser

        try {
            documentService.save(document)
        } catch (ValidationException e) {
            respond document.errors, view: 'createDocument'
            return
        }

        redirect(controller: "main", action: "userPage")
    }

    ///////////// CLERC ////////////////

    @Secured(['ROLE_CLERC'])
    def clercPage() {
        List<Document> documentList = Document.findAll()
        respond documentList, model: [documentCount: documentList.size()]

    }

    @Secured(['ROLE_CLERC'])
    def updateDocumentsStatus() {

        String[] approved = ((String) params.get("approvedDocs"))
                .replaceAll("[\\[\\](){}\"]", "")
                .split(",")

        String[] unapproved = ((String) params.get("unapprovedDocs"))
                .replaceAll("[\\[\\](){}\"]", "")
                .split(",")

        for (String id : approved) {
            Document doc = Document.findById(Long.parseLong(id))
            doc.approved = true
            documentService.save(doc)
        }
        for (String id : unapproved) {
            Document doc = Document.findById(Long.parseLong(id))
            doc.approved = false
            documentService.save(doc)
        }
        return true
    }

    @Secured(['ROLE_CLERC'])
    def getPdf(long id) {
        Document document = Document.findById(id)
        renderPdf(template: 'pdfTemplate', model: [document: document], filename: "document.pdf")
    }
    ///////////// ADMIN ////////////////

    @Secured(['ROLE_ADMIN'])
    def adminPage() {}

    ///////////// UTILS ///////////////
    private def getCurrentUser() {
        return (User) springSecurityService.getCurrentUser()
    }

    private void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
