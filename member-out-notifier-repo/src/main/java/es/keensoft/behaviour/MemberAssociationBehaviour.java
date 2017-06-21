package es.keensoft.behaviour;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.policy.Behaviour.NotificationFrequency;
import org.alfresco.repo.policy.JavaBehaviour;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.extensions.surf.util.I18NUtil;

public class MemberAssociationBehaviour implements NodeServicePolicies.OnDeleteChildAssociationPolicy {
	
	Log logger = LogFactory.getLog(MemberAssociationBehaviour.class);
	
	private PolicyComponent policyComponent;
	private ServiceRegistry serviceRegistry;
	
	public void init() {
		
		policyComponent.bindAssociationBehaviour(
				NodeServicePolicies.OnDeleteChildAssociationPolicy.QNAME,
				ContentModel.TYPE_AUTHORITY_CONTAINER,
				ContentModel.ASSOC_MEMBER,
				new JavaBehaviour(this, "onDeleteChildAssociation", NotificationFrequency.TRANSACTION_COMMIT));
	}

	@Override
	public void onDeleteChildAssociation(ChildAssociationRef childAssocRef) {
		
		NodeService nodeService = serviceRegistry.getNodeService();
		
		String authorityDisplayName = nodeService.getProperty(childAssocRef.getParentRef(), ContentModel.PROP_AUTHORITY_DISPLAY_NAME).toString();
		
		if (authorityDisplayName.startsWith("site_")) {
		
	        String[] authorityNameParts = authorityDisplayName.split("_");
			String site = authorityNameParts[1];
			String role = authorityNameParts[2];
			
			logger.warn (
				I18NUtil.getMessage("user.left.site", 
						nodeService.getProperty(childAssocRef.getChildRef(), ContentModel.PROP_FIRSTNAME) + " " +
					    nodeService.getProperty(childAssocRef.getChildRef(), ContentModel.PROP_LASTNAME),
					    nodeService.getProperty(childAssocRef.getChildRef(), ContentModel.PROP_EMAIL),
					    role,
					    site)
			);
			
		}
		
	}

	public void setPolicyComponent(PolicyComponent policyComponent) {
		this.policyComponent = policyComponent;
	}
	
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

}
