pipeline {
           agent any 
           stages {
           stage ("Checkout"){
               steps{
                      script {
                          sh """
                          rm -rf java-sample-app
                          git clone https://github.com/Bharathgsbk/java-sample-app.git 
                          cd java-sample-app;git checkout dev
                          echo "Success"
                          """
                      } 
               }
           }
           stage ("Bob The Builder ") {
               steps{
                      script {
                          sh "cd java-sample-app;mvn clean install"
                      }
               }
           }
           stage ("Jai Sonar") {
               steps {
                       script {
                           sh "cd java-sample-app;/opt/sonar/bin/sonar-scanner "
                       }
               }
           }
           }
}
        
 