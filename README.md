# Nodes  
An application written for DAT076 by Tobias Edvardsson and Marcus Flyckt  
  
# Description
Nodes is a web application built to give users the ability to send temperature data from their temperature nodes to a simple API and display the results in an intuitive way.  
  
# Technical
Nodes is built on Java EE running on Glassfish 4.1. Postgres is used as relational database system.  
  
# Getting started
After your instance of nodes is running register a new user from the register page.  

When logged in you can manage your profile settings and generate an API key, this is needed for your temperature node API calls.  
  
Each user manages his or her own nodes and collections, you need to create a node before reporting data to Nodes, from the Nodes page you can create nodes and collections and connect them together. This to enable modules to be added and display your data.  
  
From the front page either click Edit modules or to do Modules in de navigation. From this page you can attach different modules to your collections, each displaying the data connected to that collections and its module in its own inituative way.  

# Quickstart
1, Register a new account  
2, Create your desired nodes  
3, Create one or several collections and add your nodes to them  
4, Go to Modules and add modules to display your collection data
5, Set up your temperature node to report data to the API and watch data be updated on your front page in realtime!

# API
New data is to be send to:  
URL: http://NODESURL/datapoint  
Content-Type: application/json  
METHOD: POST  
HEADERS:   
    Owner: USERNAME,  
    APIKey: GENERATED FROM PROFILE SETTINGS  
BODY: { 'data': NEW VALUE, 'NodePK':{'owner': USERNAME, 'node': NODENAME}}  


