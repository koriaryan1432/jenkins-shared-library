 // vars/createEnv.groovy                                                                                                                    
    def call(Map envVars = [:]) {                                                                                                               
        echo "📝 Generating secure runtime .env file..."                                                                                        
                                                                                                                                                
        // Accumulate the map entries into standard env file format                                                                             
        def envContent = ""                                                                                                                     
        envVars.each { key, value ->                                                                                                            
            envContent += "key ={value}\n"                                                                                                      
        }                                                                                                                                       
                                                                                                                                                
        // Write content to a temporary workspace .env file                                                                                     
        writeFile file: '.env', text: envContent                                                                                                
                                                                                                                                                
        // Restrict permissions on Unix systems so only the Jenkins user can read it                                                            
        if (isUnix()) {                                                                                                                         
            sh "chmod 600 .env"                                                                                                                 
        }                                                                                                                                       
                                                                                                                                                
        echo "✅ .env file successfully created!"                                                                                               
    }
