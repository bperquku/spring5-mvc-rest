githubNotify description: 'Building',  status: 'PENDING'

checkout([
		$class                           : 'GitSCM',
		branches                         : [[name: '${githubBranch}']],
		doGenerateSubmoduleConfigurations: false,
		extensions                       : [],
		submoduleCfg                     : [],
		userRemoteConfigs                : [
		[
            credentialsId: 'e12e062f-60dd-457e-be4e-8e36c56b63dd',
			url          : 'git@github.com:bperquku/spring5-mvc-rest.git'
		]
	]
])
                
pipeline {
    agent any
    stages {
        stage('Checkout') {
			script{
			    checkout
			}
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile install -DskipTests=true'
            }
        }
        stage('Unit Testing') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Integration Testing') {
            steps {
                sh 'mvn integration-test -DskipUTs=true'
            }
        }
        stage('Coverage') {
            steps {
                //Record JaCoCo coverage report
                jacoco changeBuildStatus: true
                //Publish JaCoCo coverage report
                publishCoverage adapters: [
                        jacocoAdapter('target/site/jacoco/index.html')],
                        sourceFileResolver: sourceFiles('NEVER_STORE')
            }
        }
    }
    post {
        failure {
            //updateGitlabCommitStatus name: 'build', state: 'failed'
            //emailext attachLog: true,
            //        body: '$DEFAULT_CONTENT',
            //        recipientProviders: [
            //                developers(),
            //                culprits()],
            //        replyTo: '$DEFAULT_REPLYTO',
            //        subject: '$DEFAULT_SUBJECT',
            //        to: '$DEFAULT_RECIPIENTS'
            githubNotify description: 'Building',  status: 'FAILURE'
        }
        success {
            githubNotify description: 'Building',  status: 'SUCCESS'
        }
    }
}
