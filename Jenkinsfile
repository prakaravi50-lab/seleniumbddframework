pipeline {
    //1.Select Jenkins Agent
    agent any
    
    //2.Configure java ana maven
   /* tools{
        jdk 'JDK21'
        maven 'Maven-3.9.16'
    }*/
    //3.Pipeline configuration
    options{
        timestamps()
        timeout(time:30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '2'))
    }
    
    //4. Define pipeline stages
    stages {
        //Stage 1: Checkout source code
        stage('Git checkout') {
            steps {
                echo 'Checkout Automation codes'
                checkout scm
            }
        }
            
        //Stage 2: Verify java and maven
        stage('Environment Setup'){
            steps{
                bat 'java -version'
                bat 'mvn -version'
            }
        }
        //Stage 3: Compile project
        stage('Build')
        {
            steps{
                echo 'Building Selenium Automation Project'
                bat 'mvn clean compile test-compile'
            }
        }
        //Stage 4: Execute TestNG automation
        stage('Execute the Automation TestNG')
        {
            steps{
                echo 'Executing Selenium TestNG Test'
                bat 'mvn test'
            
            }
        }
            
        //Stage 5: Automation Completion
        stage('Automation Completion'){
            steps{
                echo 'Automation Execution Completed'
                
            }
        }
     }
     //5. Post Execution Activities 
     post{
         always{
             echo 'Publishing TestNG execution reports'

            junit testResults: 'target/surefire-reports/*.xml',
                  allowEmptyResults: false

            archiveArtifacts(
                artifacts: 'target/surefire-reports/**',
                allowEmptyArchive: true
            )
         }
          success {
            echo 'SUCCESS: Automation pipeline passed'
        }

        failure {
            echo 'FAILURE: Automation pipeline failed'
        }

        aborted {
            echo 'Pipeline was aborted'
        }
     }
   }
