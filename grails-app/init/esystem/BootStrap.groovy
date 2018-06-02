package esystem

class BootStrap {

    def init = { servletContext ->
//        def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
//        def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
//        def clercRole = new Role(authority: 'ROLE_CLERC').save(flush: true)
//
//        def admin = new User(username: 'admin', enabled: true, password: 'admin',
//                pesel: '11111111111', email: 'admin@admin.com',
//                firstName: 'admin', lastName: 'admin')
//
//        def clerc = new User(username: 'clerc', enabled: true, password: 'clerc',
//                pesel: '22222222222', email: 'clerc@clerc.com',
//                firstName: 'clerc', lastName: 'clerc')
//
//        admin.save(flush: true)
//        clerc.save(flush: true)

//        UserRole.create(admin, adminRole, true)
//        UserRole.create(clerc, clercRole, true)

//        UserRole.withSession {
//            it.flush()
//        }

//        assert User.count() == 2
//        assert Role.count() == 3
//        assert UserRole.count() == 2
    }

    def destroy = {
    }
}
