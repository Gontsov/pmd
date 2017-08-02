/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.metrics.signature;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Signature mask for an operation. Newly created masks cover any operation that is not abstract.
 *
 * @author Clément Fournier
 */
public final class OperationSigMask extends SigMask<JavaOperationSignature> {

    private Set<JavaOperationSignature.Role> roleMask = new HashSet<>();
    private boolean coverAbstract = false;


    public OperationSigMask() {
        super();
        coverAllRoles();
    }


    /**
     * Sets the mask to cover all roles.
     */
    public void coverAllRoles() {
        roleMask.addAll(Arrays.asList(JavaOperationSignature.Role.values()));
    }


    /**
     * Restricts the roles covered by the mask to the parameters.
     *
     * @param roles The roles to cover
     */
    public void restrictRolesTo(JavaOperationSignature.Role... roles) {
        roleMask.clear();
        roleMask.addAll(Arrays.asList(roles));
    }


    /**
     * Forbid all mentioned roles.
     *
     * @param roles The roles to forbid
     */
    public void forbid(JavaOperationSignature.Role... roles) {
        roleMask.removeAll(Arrays.asList(roles));
    }


    /**
     * Forbid all mentioned visibilities.
     *
     * @param coverAbstract The visibilities to forbid
     */
    public void coverAbstract(boolean coverAbstract) {
        this.coverAbstract = coverAbstract;
    }


    @Override
    public boolean covers(JavaOperationSignature sig) {
        return super.covers(sig) && roleMask.contains(sig.role) && (coverAbstract
            || !sig.isAbstract);
    }
}