package com.alla.sharai

import grails.plugin.springsecurity.annotation.Secured

class MainController {

    def springSecurityService

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index() {}

    @Secured(['ROLE_USER'])
    def userPage() {
        User currentUser = (User) springSecurityService.getCurrentUser()
        List<Document> documentList = Document.findAllByOwner(currentUser)
        respond documentList, model: [documentCount: documentList.size()]
    }

    @Secured(['ROLE_CLERC'])
    def clercPage() {}

    @Secured(['ROLE_ADMIN'])
    def adminPage() {}

}
