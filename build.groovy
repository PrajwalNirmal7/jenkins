pipeline {
    agent { 
        label 'node' 
        }
    stages {
        stage('Cloning the Git Repository') {
            steps {
                echo 'clone repositery consist of build packages'
                git branch: 'main', url: 'https://github.com/PrajwalNirmal7/jenkins.git'
                  }
             }
        stage('Installation of Aws Cli') {
            steps {
                sh '''
                echo "installing aws cli"
                sudo apt update
                sudo apt install awscli -y 
                '''
            }
        }     
        stage('CloudFormation On Cloud') 
        {
            steps {
            sh '''
            echo "CFT Run"
            cd /home/ubuntu/workspace/project-1
            aws cloudformation create-stack --stack-name mystacknew --template-body file:///home/ubuntu/workspace/cloudpipeline/cloudformation.yaml --region us-east-1
            '''
            }
        }
    }
}
