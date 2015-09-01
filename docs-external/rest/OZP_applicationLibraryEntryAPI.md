#API Object: marketplace.ApplicationLibraryEntry

##Definition 
Use the `/ApplicationLibraryEntry` API to create, update, read or delete an application library entry in the system. 

##Resource Information
The following properties appear in the Application Library Entry JSON:

<table style="width:100%">
    <thead>
        <tr>
            <td><b>Parameter</b></td>
            <td><b>Description</b></td
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>folder</td>
            <td>The name of a folder in the application library.</td> 
        </tr>
        <tr>
            <td>listing/id</td>
            <td>Name of a listing in the application library/id associated with the listing.</td> 
        </tr>
        <tr>
            <td>title</td>
            <td>The name of the type.</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/profile/2/library`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_applicationLibraryEntryAPI.md#POST),
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_applicationLibraryEntryAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_applicationLibraryEntryAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_applicationLibraryEntryAPI.md#DELETE)
<br>
Each method will be explained in the following sections:

###<a name=POST>POST</a>
Use this call to **create** a library with one folder in the system.

#####Request
######URL
`https://localhost:8443/marketplace/api/profile/2/library`
######Headers
    Accept = application/JSON
    Content-Type = application/JSON
######Body
	{  
    	"folder":"folder 1",
    	"listing":{  
    	    "id":173
    	}
	}
#####Response Code:
201

#####Response

	{  
    	"folder":"folder 1",
    	"listing":{  
    	    "id":173,
    	    "launchUrl":"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Hulkling-Sulking-icon.png",
    	    "uuid":"5114a1d4-05cb-44f3-a34a-571e34480490",
    	    "title":"Listing1",
    	    "imageXlargeUrl":null,
    	    "imageMediumUrl":null,
    	    "imageLargeUrl":null,
    	    "imageSmallUrl":null
    	}
	}

#####Requirements
none
<br>




###<a name=PUT>PUT</a>###
Use this call to **update** a library in the system. The following example deletes a listing from a folder in the library. 
#####Request
######URL
`https://localhost:8443/marketplace/api/profile/2/library`
######Headers
    Accept = application/JSON
    Content-Type = application/JSON
######Body
	[  
    	{  
    	    "folder":"Folder 1",
    	    "listing":{  
    	        "id":227
    	    }
    	},
    	{  
    	    "folder":"Folder 1",
    	    "listing":{  
    	        "id":228
    	    }
    	},
    	{  
    	    "folder":"Folder 1",
    	    "listing":{  
    	        "id":230
    	    }
    	}
	]

#####Response Code:
200

#####Response
    
	[
    	{
    	    "folder":"Folder 1",
    	    "listing":
    	    {
    	        "id":227,
    	        "launchUrl":"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Hulkling-Sulking-icon.png",
    	        "uuid":"3a345233-c989-4cf4-bc85-22b6e7c24f44",
    	        "title":"Listing1",
    	        "imageXlargeUrl":null,
    	        "imageMediumUrl":null,
    	        "imageLargeUrl":null,
    	        "imageSmallUrl":null
    	    }
    	},
    	{
    	    "folder":"Folder 1",
    	    "listing":
    	    {
    	        "id":228,
    	        "launchUrl":"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Hulkling-Sulking-icon.png",
    	        "uuid":"840caabf-c392-4e27-99cd-008cac1ec967",
    	        "title":"Listing2",
    	        "imageXlargeUrl":null,
    	        "imageMediumUrl":null,
    	        "imageLargeUrl":null,
    	        "imageSmallUrl":null
    	    }
    	},
    	{
    	    "folder":"Folder 1",
    	    "listing":
    	    {
    	        "id":230,
    	        "launchUrl":"http://icons.iconarchive.com/icons/mattahan/ultrabuuf/128/Comics-Hulkling-Sulking-icon.png",
    	        "uuid":"3b4ea74b-b2b9-4730-9174-3aaf548ece55",
    	        "title":"Listing4",
    	        "imageXlargeUrl":null,
    	        "imageMediumUrl":null,
    	        "imageLargeUrl":null,
    	        "imageSmallUrl":null
    	    }
    	}
	]

#####Requirements
none
<br>
<br>


###<a name=GET>GET</a>###
Use this call to **read or view** an application in the system.
#####Request
`https://localhost:8443/marketplace/api/profile/2/application`
 
The following example shows the response for an application with a single folder. 

#####Response Code:
200

#####Response for an application with a single folder

	{
    	"_links":
    	{
    	    "item":
    	    [
    	        {
    	            "href":"https://localhost:8443/marketplace/api/listing/173"
    	        },
    	        {
    	            "href":"https://localhost:8443/marketplace/api/listing/174"
    	        },
    	        {
    	            "href":"https://localhost:8443/marketplace/api/listing/175"
    	        },
    	        {
    	            "href":"https://localhost:8443/marketplace/api/listing/176"
    	        }
    	    ],
    	    "self":
    	    {
    	        "href":"https://localhost:8443/marketplace/api/profile/2/application"
    	    }
    	}
	}

#####Requirements
none


<br>
<br>
<br>

###<a name=DELETE>DELETE</a>###
Use this call to remove something from the library. The following example removes a folder {id} from the library 
#####Requirements
`https://localhost:8443/marketplace/api/profile/2/library/{id}`

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
            <td>400</td>
            <td>Update a folder (bad request)</td>
            <td>The folder name must be 256 characters or less.</td> 
        </tr>  
    </tbody>
</table> 



