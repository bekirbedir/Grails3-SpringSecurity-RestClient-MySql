package reybexmobil2

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class SecurityUserSecurityRole implements Serializable {

	private static final long serialVersionUID = 1

	SecurityUser securityUser
	SecurityRole securityRole

	@Override
	boolean equals(other) {
		if (other instanceof SecurityUserSecurityRole) {
			other.securityUserId == securityUser?.id && other.securityRoleId == securityRole?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (securityUser) {
            hashCode = HashCodeHelper.updateHash(hashCode, securityUser.id)
		}
		if (securityRole) {
		    hashCode = HashCodeHelper.updateHash(hashCode, securityRole.id)
		}
		hashCode
	}

	static SecurityUserSecurityRole get(long securityUserId, long securityRoleId) {
		criteriaFor(securityUserId, securityRoleId).get()
	}

	static boolean exists(long securityUserId, long securityRoleId) {
		criteriaFor(securityUserId, securityRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long securityUserId, long securityRoleId) {
		SecurityUserSecurityRole.where {
			securityUser == SecurityUser.load(securityUserId) &&
			securityRole == SecurityRole.load(securityRoleId)
		}
	}

	static SecurityUserSecurityRole create(SecurityUser securityUser, SecurityRole securityRole, boolean flush = false) {
		def instance = new SecurityUserSecurityRole(securityUser: securityUser, securityRole: securityRole)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(SecurityUser u, SecurityRole r) {
		if (u != null && r != null) {
			SecurityUserSecurityRole.where { securityUser == u && securityRole == r }.deleteAll()
		}
	}

	static int removeAll(SecurityUser u) {
		u == null ? 0 : SecurityUserSecurityRole.where { securityUser == u }.deleteAll() as int
	}

	static int removeAll(SecurityRole r) {
		r == null ? 0 : SecurityUserSecurityRole.where { securityRole == r }.deleteAll() as int
	}

	static constraints = {
	    securityUser nullable: false
		securityRole nullable: false, validator: { SecurityRole r, SecurityUserSecurityRole ur ->
			if (ur.securityUser?.id) {
				if (SecurityUserSecurityRole.exists(ur.securityUser.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['securityUser', 'securityRole']
		version false
	}
}
