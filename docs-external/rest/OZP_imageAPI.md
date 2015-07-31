#API Object: marketplace.image

##Definition 
Use the `/image` API to create or read an image or list of images in the system. 

##Resource Information
The following properties appear in the image JSON:

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
            <td>The numerical ID associated with the image.</td> 
        </tr>
        <tr>
            <td>contentType</td>
            <td>The format of the object, e.g., image/png.</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/image`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_imageAPI.md#POST),
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_imageAPI.md#GET)

<br>
Each method will be explained in the following sections:

###<a name=POST>POST</a>
Use this call to **create** an image in the system.

#####Request
`https://localhost:8443/marketplace/api/image`

	<actual file content, not shown in example> 

#####Response Code:
201

#####Response

	{  
    	"id":"24210079-9ef7-4cdc-b16e-ed943afbd989",
    	"_links":{  
    	    "self":{  
    	        "href":"https://localhost:8443/marketplace/api/image/24210079-9ef7-4cdc-b16e-ed943afbd989.webp"
    	    }
    	},
    	"contentType":"image/webp"
	}

#####Requirements
none
<br>


###<a name=GET>GET</a>###
Use this call to **read or view** an image or all the images in the system.
#####Request
If you want to see a list of all the types in the system, enter:
`https://localhost:8443/marketplace/api/image/`

However, to view metadata about only one type, enter:
`https://localhost:8443/marketplace/api/image/{id}`
 
Marketplace (i.e., Center) returns the actual image that matches the{id}. 

#####Response Code:
200

#####Response for one type id

	You should see the image you queried.

#####Requirements
none





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
            <td>
            <td>Cannot find the image
            <td>You need the correct UUID and mediaType. </td> 
        </tr>
    </tbody>
</table> 



