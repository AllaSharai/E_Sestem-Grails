package esystem

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/main/index")
        "/grails"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
