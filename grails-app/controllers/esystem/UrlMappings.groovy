package esystem

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/main/index")
        "/userPage"(view:"/main/userPage")
        "/clercPage"(view:"/main/clercPage")
        "/adminPage"(view:"/main/adminPage")
        "/grails"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
