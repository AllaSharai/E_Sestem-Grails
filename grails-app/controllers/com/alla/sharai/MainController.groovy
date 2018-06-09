package com.alla.sharai

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

class MainController {

    def springSecurityService
    DocumentService documentService


    static allowedMethods = [saveDocument: "POST", updateDocumentsStatus: "POST"]

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
    def clercPage() {
        List<Document> documentList = Document.findAll()
        respond documentList, model: [documentCount: documentList.size()]

    }

    @Secured(['ROLE_CLERC'])
    def updateDocumentsStatus() {

        String[] approved = ((String) params.get("approvedDocs"))
                .replaceAll("[\\[\\](){}\"]","")
                .split(",")

        String[] unapproved = ((String) params.get("unapprovedDocs"))
                .replaceAll("[\\[\\](){}\"]","")
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

    ///////////// ADMIN ////////////////

    @Secured(['ROLE_ADMIN'])
    def adminPage() {}


    ///////////// UTILS ///////////////
    private def getCurrentUser() {
        return (User) springSecurityService.getCurrentUser()
    }
}
