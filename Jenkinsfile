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
    
    triggers {
    //cron('* * * * *')
     pollSCM('H/5 * * * *')
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
            emailext(
            to: 'prakaravi50@gmail.com',
            subject: "SUCCESS: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
            mimeType: 'text/html',
            body: """
                <h2 style="color:green;">Automation Pipeline SUCCESS</h2>

                <p>Hello QA Team,</p>

                <p>Selenium automation execution completed successfully.</p>
                <table border="1" cellpadding="5">
                    <tr>
                        <td>Project</td>
                        <td>${env.JOB_NAME}</td>
                    </tr>
                    <tr>
                        <td>Build Number</td>
                        <td>${env.BUILD_NUMBER}</td>
                    </tr>
                    <tr>
                        <td>Build Status</td>
                        <td>SUCCESS</td>
                    </tr>
                    <tr>
                        <td>Build URL</td>
                        <td><a href="${env.BUILD_URL}">View Build</a></td>
                    </tr>
                </table>

                <p>Regards,<br>Jenkins CI/CD</p>
            """
        )
        }

        failure {
            echo 'FAILURE: Automation pipeline failed'
            emailext(
            to: 'prakaravi50@gmail.com',
            subject: "FAILURE: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
            mimeType: 'text/html',
            body: """
                <h2 style="color:red;">Automation Pipeline FAILED</h2>

                <p>Hello QA Team,</p>

                <p>Selenium automation execution has failed.</p>

                <table border="1" cellpadding="5">
                    <tr>
                        <td>Project</td>
                        <td>${env.JOB_NAME}</td>
                    </tr>
                    <tr>
                        <td>Build Number</td>
                        <td>${env.BUILD_NUMBER}</td>
                    </tr>
                    <tr>
                        <td>Build Status</td>
                        <td>FAILURE</td>
                    </tr>
                    <tr>
                        <td>Build URL</td>
                        <td><a href="${env.BUILD_URL}">View Build</a></td>
                    </tr>
                    <tr>
                        <td>Test Report</td>
                        <td><a href="${env.BUILD_URL}testReport/">
                            View Test Results
                        </a></td>
                    </tr>
                </table>

                <p>Please investigate the failed test cases.</p>

                <p>Regards,<br>Jenkins CI/CD</p>
            """
        )
        }

        aborted {
            echo 'Pipeline was aborted'
        }
     }
   }
