#API Object: marketplace.profile

##Definition 
Use the `/profile` API to update or read a profile or list of profiles in the system. 

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
            <td>id</td>
            <td>The numerical ID associated with the profile object.</td> 
        </tr>
        <tr>
            <td>displayName</td>
            <td>How the user/profile owner's name appears in the user interface.</td> 
        </tr>
        <tr>
            <td>createDate</td>
            <td>When the profile was created.</td> 
        </tr>
        <tr>
            <td>launchInWebtop</td>
            <td>A true/false value configured by the user to decide how listings will automatically launch (in Webtop or a new browser tab).</td> 
        </tr>
        <tr>
            <td>organizations</td>
            <td>The user's organization(s)</td> 
        </tr>
        <tr>
            <td>highestRole</td>
            <td>Defines if the user is a basic user, org steward or center steward</td> 
        </tr>
        <tr>
            <td>bio</td>
            <td>This field is not active at this time.</td> 
        </tr>
        <tr>
            <td>lastLogin</td>
            <td>The last time the user entered the system.</td> 
        </tr>
        <tr>
            <td>email</td>
            <td>The user's email address</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/profile`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_profileAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_profileAPI.md#GET), 
<br>
Each method will be explained in the following sections:



###<a name=PUT>PUT</a>###
Use this call to **update** a profile in the system.
#####Request
`https://localhost:8443/marketplace/api/profile/{id}`

    {  
    "id":3,
    "bio":"Bio",
    "displayName":"Test Admin 1",
    "uuid":"unknown",
    "username":"testAdmin1",
    "email":"testAdmin1@nowhere.com"
    }

#####Response Code:
200

#####Response
    
	{  
    	"id":3,
    	"displayName":"Test User 1",
    	"username":"testUser1",
    	"createdDate":"2015-07-14T20:38:01.529+0000",
    	"launchInWebtop":true,
    	"organizations":[  

    	],
    	"stewardedOrganizations":[  

    	],
    	"highestRole":"USER",
    	"bio":"Admin updating user bio",
    	"lastLogin":"2015-07-30T17:22:15.710+0000",
    	"email":"testUser1@nowhere.com",
    	"_links":{  
    	    "curies":{  
    	        "href":"http://ozoneplatform.org/docs/rels/{rel}",
    	        "name":"ozp",
    	        "templated":true
    	    },
    	    "ozp:application-library":{  
    	        "href":"https://localhost:8443/marketplace/api/profile/3/library"
    	    },
    	    "ozp:user-data":{  
    	        "href":"https://localhost:8443/marketplace/api/profile/3/data"
    	    },
    	    "self":{  
    	        "href":"https://localhost:8443/marketplace/api/profile/3"
    	    }
    	}
	}

#####Requirements
none
<br>
<br>


###<a name=GET>GET</a>###
Use this call to **read or view** a profile or all the profiles in the system.
#####Request
If you want to see a list of all the profiles in the system, enter:
`https://localhost:8443/marketplace/api/profile/`

However, to view metadata about only one profile, enter:
`https://localhost:8443/marketplace/api/profile/{id}`
 
Marketplace (i.e., Center) returns the representation of the profile that matches the{id}, as shown in the Response for one profile id. 

#####Response Code:
200

#####Response for one profile id
    
    {  
    	"id":3,
    	"displayName":"Test User 1",
    	"username":"testUser1",
    	"createdDate":"2015-07-14T20:38:01.529+0000",
    	"launchInWebtop":true,
    	"organizations":[  
    
    	],
    	"stewardedOrganizations":[  
    
    	],
    	"highestRole":"USER",
    	"bio":"Admin updating user bio",
    	"lastLogin":"2015-07-30T13:22:35.163+0000",
    	"email":"testUser1@nowhere.com",
    	"_links":{  
    		"curies":{  
    			"href":"http://ozoneplatform.org/docs/rels/{rel}",
    			"name":"ozp",
    			"templated":true
    		},
    		"ozp:application-library":{  
    			"href":"https://localhost:8443/marketplace/api/profile/3/library"
    		},
    		"ozp:user-data":{  
    			"href":"https://localhost:8443/marketplace/api/profile/3/data"
    		},
    		"self":{  
    			"href":"https://localhost:8443/marketplace/api/profile/3"
    		}
    	}
    }

#####Requirements
none

<br>
<br>
<br>







