
Alfresco Member Leaving Site Detector
=====================================

This add-on writes a simple line at Alfresco repository log `alfresco.log` and Tomca log `catalina.out` everytime a user leaves a site.

```
WARN  [keensoft.behaviour.MemberAssociationBehaviour] [http-bio-8080-exec-6] 
User Alice Beecher (abeecher@example.com) left role "SiteManager" at site "swsdp"
```

**License**
The plugin is licensed under the [LGPL v3.0](http://www.gnu.org/licenses/lgpl-3.0.html). 

**State**
Current addon release is 1.0.0

**Compatibility**
The current version has been developed using Alfresco 201702 and Alfresco SDK 3.0.0

***No original Alfresco resources have been overwritten***

Downloading the ready-to-deploy-plugin
--------------------------------------
The binary distribution is made of one JAR file to be deployed in Alfresco as a repo module:

* [repo JAR](https://github.com/keensoft/alfresco-member-leaving-site-detector/releases/download/1.0.0/member-out-notifier-repo-1.0.0.jar)

You can install it by copying JAR file to `$ALFRESCO_HOME/modules/platform` and re-starting Alfresco.


Building the artifacts
----------------------
You can build the artifacts from source code using maven
```$ mvn clean package```
