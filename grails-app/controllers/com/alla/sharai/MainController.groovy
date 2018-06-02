package com.alla.sharai

import grails.plugin.springsecurity.annotation.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class MainController {

    def index() { }

    def userPage() { }

    def clercPage() { }

    def adminPage() { }


}
