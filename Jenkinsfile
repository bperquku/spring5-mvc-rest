githubNotify description: 'Building',  status: 'PENDING'
githubNotify account: 'bperquku', context: 'CI/CD', credentialsId: 'e12e062f-60dd-457e-be4e-8e36c56b63dd',
    description: 'This is an example', repo: 'spring5-mvc-rest', sha: '0b5936eb903d439ac0c0bf84940d73128d5e9487',status: 'SUCCESS', targetUrl: 'https://http://ec2-3-91-62-19.compute-1.amazonaws.com:8080'
    
    
pipeline {
    agent any
    stages {
        stage('Checkout') {
			steps{
			    checkout([
						$class                           : 'GitSCM',
						branches                         : [[name: 'master']],
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
