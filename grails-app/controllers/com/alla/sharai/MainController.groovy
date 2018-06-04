package com.alla.sharai

import grails.plugin.springsecurity.annotation.Secured

class MainController {

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index() { }

    @Secured(['ROLE_USER'])
    def userPage() { }

    @Secured(['ROLE_CLERC'])
    def clercPage() { }

    @Secured(['ROLE_ADMIN'])
    def adminPage() { }

}
