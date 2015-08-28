#API Object: marketplace.notification

##Definition 
Use the `/notification` API to create, update, read or delete a type or list of types in the system. 

##Resource Information
The following properties appear in the Type JSON:

<table style="width:100%">
    <thead>
        <tr>
            <td><b>Parameter</b></td>
            <td><b>Description</b></td
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>message</td>
            <td>The text sent as a notification.</td> 
        </tr>
        <tr>
            <td>id</td>
            <td>The notification identifier.</td> 
        </tr>
        <tr>
            <td>expiresDate</td>
            <td>Date when the notification will dispear from users' drop-down notification list.</td> 
        </tr>
        <tr>
            <td>createdDate</td>
            <td>Date when the notification entered the system.</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/notification`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_notificationAPI.md#POST),
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_notificationAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_notificationAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_notificationAPI.md#DELETE)
<br>
Each method will be explained in the following sections:

###<a name=POST>POST</a>
Use this call to **create** a notification in the system.

#####Request
#######URL
`https://localhost:8443/marketplace/api/notification`
#######Heading
    Accept = application/JSON
    Content-Type = application/JSON
#######Body
    {
      "expiresDate": "2015-01-29T18:25:00Z",
      "message": "Description"
    }


#####Response Code:
201

#####Response

    {
    "message":"Description",
    "id":51,
    "expiresDate":"2015-01-29T18:25:00.000+0000",
    "createdDate":"2015-07-28T17:57:09.838+0000",
    "_links":
    {
    "self":
    {
    "href":"https://localhost:8443/marketplace/api/notification/51"
    }
    }
    }

#####Requirements
none
<br>




###<a name=PUT>PUT</a>###
Use this call to **update** a notification in the system.
#####Request
######URL
`https://localhost:8443/marketplace/api/notification/{id}`
#######Heading
    Accept = application/JSON
    Content-Type = application/JSON
######Body
      {
    "id":60,
    "expiresDate": "2015-01-29T18:25:00Z",
    "message": "123456"
      }
    

#####Response Code:
200

#####Response
    
    {
        "message":"123456",
        "id":60,
        "expiresDate":"2015-01-29T18:25:00.000+0000",
        "createdDate":"2015-07-28T17:57:10.400+0000",
        "_links":
        {
            "self":
            {
                "href":"https://localhost:8443/marketplace/api/notification/60"
            }
        }
    }

#####Requirements
none
<br>
<br>


###<a name=GET>GET</a>###
Use this call to **read or view** a type or all the notifications in the system.
#####Request
If you want to see a list of all the notifications in the system, enter:
`https://localhost:8443/marketplace/api/notification/`

However, to view metadata about only one notification, enter:
`https://localhost:8443/marketplace/api/notification/{id}`
 
Marketplace (i.e., Center) returns the representation of the type that matches the{id}, as shown in the Response for one type id. 

#####Response Code:
200

#####Response for one type id

    {  
    	  "total":1,
    	  "_links":{  
    	    "item":{  
    	    	"href":"https://localhost:8443/marketplace/api/notification/101"
    		},
    		"self":{  
    			"href":"https://localhost:8443/marketplace/api/notification"
    		}
    	},
    	"_embedded":{  
    		"item":{  
    			"message":"System upgrade. Plan for a reboot. ",
    			"id":101,
    			"expiresDate":"2015-08-02T04:00:00.000+0000",
    			"createdDate":"2015-07-28T19:12:16.731+0000",
    			"_links":{  
    				"self":{  
    					"href":"https://localhost:8443/marketplace/api/notification/101"
    				}
    			}
    		}
    	}
    }

#####Requirements
none


###<a name=DELETE>DELETE</a>###
Use this call to remove a notification from the system.
#####Requirements
`https://localhost:8443/marketplace/api/notification/{id}`

#####Response Code:
204

#####Response
no content<br>    
       
#####Requirements
none

<br>
<br>




###Possible Errors

This table lists common errors. Other errors may occur but these are the most likely:
<table style="width:100%">
    <thead>
        <tr>    
            <td><b>Error <br> Code</b></td>
            <td><b>Error</b></td>
            <td><b>Troubleshooting</b></td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>404
            <td>Invalid ID
            <td>The notification you are looking for may not exist (i.e., it was deleted) or has a different ID.</td> 
        </tr>
    </tbody>
</table> 



