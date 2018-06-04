package com.alla.sharai

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED

class MainController {

    def springSecurityService
    DocumentService documentService


    static allowedMethods = [saveDocument: "POST"]

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index() {}

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
    def clercPage() {}

    ///////////// ADMIN ////////////////

    @Secured(['ROLE_ADMIN'])
    def adminPage() {}


    ///////////// UTILS ///////////////
    private def getCurrentUser() {
        return (User) springSecurityService.getCurrentUser()
    }
}
