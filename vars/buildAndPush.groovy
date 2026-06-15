// vars/buildAndPush.groovy                                                                                                                 
    def call(Map config = [:]) {                                                                                                                
        // Expected config parameters:                                                                                                          
        // - imageName: e.g., 'koriaryan1432/shopease-backend'                                                                                  
        // - buildContext: e.g., './backend'                                                                                                    
        // - buildTag: e.g., env.BUILD_NUMBER                                                                                                   
                                                                                                                                                
        String fullImageName = "config.imageName:{config.buildTag}"                                                                             
                                                                                                                                                
        echo "📦 Building Docker image: ${fullImageName}..."                                                                                    
        if (isUnix()) {                                                                                                                         
            sh "docker build -t ${fullImageName} ${config.buildContext}"                                                                        
            echo "🚀 Pushing image to registry..."                                                                                              
            sh "docker push ${fullImageName}"                                                                                                   
        } else {                                                                                                                                
            bat "docker build -t ${fullImageName} ${config.buildContext}"                                                                       
            echo "🚀 Pushing image to registry..."                                                                                              
            bat "docker push ${fullImageName}"                                                                                                  
        }                                                                                                                                       
        echo "✅ Image successfully published!"                                                                                                 
    }                
