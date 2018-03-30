package reybexmobil2

class BootStrap {

    def init = { servletContext ->
      /*  def userRole = new SecurityRole(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = new SecurityRole(authority: 'ROLE_ADMIN').save(failOnError: true)

        def userUser = new SecurityUser(username: 'user', password: 'user',enabled: true).save(failOnError: true)
        def adminUser = new SecurityUser(username: 'admin', password: 'admin',enabled: true).save(failOnError: true)

        SecurityUserSecurityRole.create(userUser,userRole)
        SecurityUserSecurityRole.create(adminUser,adminRole)
    */
    }
    def destroy = {
    }
}
