pipeline {
    agent any
    environment {
        PRD = "PROD"
        DEV = "DEV"
        UAT = "ACC"
        GIT_URL = "https://github.com/crazy4devops/demo-java.git"
        ARTIFACTORY_URL = "http://172.31.35.111:8082/artifactory/example-repo-local"
        ARTIFACTORY_USERNAME = "admin"
        ARTIFACTORY_TOKEN = "APEyaa4LF6xpr7DLVGc5UNVduE "
        REPO_DIR = "java-sample-app"
    }
    stages {
        stage ("Checkout SCM"){
            steps {
                script{
                    git url: "${GIT_URL}", branch: 'test'
                }
            }
        }
        stage ("Build Source Code"){
            steps {
                script{
                     sh "mvn clean package"
                }
            }
        }
        // stage ("Code Analysis"){
        //     steps {
        //         script {
        //             sh "/opt/sonar/bin/sonar-scanner"
        //         }
        //     }
        // }
       stage ("Rename Artifact") {
            steps {
                script {
                    sh "mv target/demo.war target/demo-${BUILD_NUMBER}.war"
                }
            }
        }
        // stage ("Upload Artifacts") {
        //     steps {
        //         script {
        //             sh "curl -u${ARTIFACTORY_USERNAME}:${ARTIFACTORY_TOKEN} \
        //             -T target/demo-${BUILD_NUMBER}.war \
        //              ${ARTIFACTORY_URL}/demo-${BUILD_NUMBER}.war"
        //         }
        //     }
        // }
        stage ("Rename artifact to Original") {
            steps {
                script {
                    sh "mv target/demo-${BUILD_NUMBER}.war target/demo.war"
                }
            }
        }
        stage("deploy-dev"){
           steps{

            //    sshagent(credentials: ['ashk-aws-creds']) {
            //         // some block
            //        // sh 'ssh -o StrictHostKeyChecking=no -l ubuntu ec2-3-144-231-61.us-east-2.compute.amazonaws.com uname -a'
            //         sh """
                   
            //                         scp -o StrictHostKeyChecking=no target/demo-${BUILD_NUMBER}.war  ubuntu@ec2-3-144-231-61.us-east-2.compute.amazonaws.com:/home/ubuntu
            //                        # ssh cloud_user@3131060d7d1c.mylabserver.com /opt/tomcat/latest/bin/shutdown.sh
            //                        # ssh cloud_user@3131060d7d1c.mylabserver.com /opt/tomcat/latest/bin/startup.sh
            //         """
            //     }

                sshagent(credentials: ['bh-aws-ec2-creds']) { 
                    // some block
                   // sh 'ssh -o StrictHostKeyChecking=no -l ubuntu ec2-3-144-231-61.us-east-2.compute.amazonaws.com uname -a'
                    sh """
                   
                                   scp -o StrictHostKeyChecking=no target/demo.war  ubuntu@ec2-35-182-168-138.ca-central-1.compute.amazonaws.com:/opt/tomcat/latest/webapps
                                   ssh ubuntu@ec2-35-182-168-138.ca-central-1.compute.amazonaws.com sudo  systemctl restart tomcat
                                   # ssh cloud_user@3131060d7d1c.mylabserver.com /opt/tomcat/latest/bin/shutdown.sh
                                   # ssh cloud_user@3131060d7d1c.mylabserver.com /opt/tomcat/latest/bin/startup.sh
                    """
                }
                
             
            }
        }
        stage ("Run Unit Test"){
            steps {
                echo "Running Unit Test Cases"
            }
        }
        stage ("Deploy Dev"){
            steps {
                echo "Deploying.....${DEV}"
            }
        }
        stage ("Deploy UAT"){
            steps {
                echo "Deploying.....${UAT}"
            }
        }

        stage ("Deploy PRD"){
            input{
                message "Do you want to proceed for production deployment?"
            }
            steps {
                echo "Deploying.....${PRD}"
            }
        }
        sshagent(credentials: [bh-aws-ec2-creds'])
        {
            scp -o StrictHostKeyChecking=no target/demo.war ubuntu@ec2-35-182-168-138.ca-central-1.compute.amazonaws.com:/opt/tomcat/latest/webapps
            ssh "ubuntu@ec2-35-182-168-138.ca-central-1.compute.amazonaws.com sudo systemctl restart tomcat" 
        }

    }
}
def getVersion(){
    echo "Priting the Version..... 1.15"
}
def uploaddArtifact(){
    echo "uploading the artifacts...."
}