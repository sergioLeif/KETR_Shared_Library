package org.bbva.ketr.sharedLibraries

//@GrabResolver(name='CENTRAL', root='http://cibartifactory.igrupobbva:8084/artifactory/repo1' , m2Compatible=true)
//@Grapes(
//    @Grab(group='org.jfrog.artifactory.client', module='artifactory-java-client-services', version='0.16')
//)

//import org.jfrog.artifactory.client.Artifactory;
//import org.jfrog.artifactory.client.ArtifactoryClient;
//import org.jfrog.artifactory.client.model.Folder;

class KETRUtilities implements Serializable {
    
	def steps
	def envVars = Jenkins.instance.getGlobalNodeProperties()[0].getEnvVars() 
    
	/**
	 * Constructor
	 * 
	 * @param steps
	 */
	KETRUtilities(steps) {
		this.steps = steps
	}
    
   def createPackage(WORKSPACE, MAVEN_VERSION, JAVA_VERSION){
   		//steps.sh "echo Ejecutando package de ketr"
   		//steps.sh "echo Packaging "	
		//steps.sh "echo $WORKSPACE"
		steps.bat "echo Ejecutando package de ketr"
   		steps.bat "echo Packaging "	
		steps.bat "echo $WORKSPACE"
		steps.withMaven (	maven : MAVEN_VERSION,
		jdk : JAVA_VERSION) { 	
			//steps.sh "mvn --version"
			//steps.sh "mvn -DXms512m -DXmx2048m -DXX:PermSize=128m -DXX:MaxPermSize=128m -DXX:+CMSClassUnloadingEnabled -DXX:+CMSPermGenSweepingEnabled -DXX:+UseConcMarkSweepGC clean package -Dmaven.repo.local=${WORKSPACE}/.repository -DskipTests"
			steps.bat "mvn --version"
			steps.bat "mvn -DXms512m -DXmx2048m -DXX:PermSize=128m -DXX:MaxPermSize=128m -DXX:+CMSClassUnloadingEnabled -DXX:+CMSPermGenSweepingEnabled -DXX:+UseConcMarkSweepGC clean package -Dmaven.repo.local=${WORKSPACE}/.repository -DskipTests"
		}
			
	}
		
	def createReleaseStructure(WORKSPACE, UUAA){
		try {
			def RS = envVars['RUTA_SCRIPT_DEVOPS']
			steps.sh "${RS}/RecuperacionJARs.sh $WORKSPACE $UUAA"
			//steps.bat "${RS}/RecuperacionJARs.sh $WORKSPACE $UUAA"
		} catch (err){
			throw err
			steps.sh "exit -1"
		}
	}
}