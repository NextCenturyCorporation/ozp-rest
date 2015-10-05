#API Object: marketplace.type

##Definition 
Use the `/type` API to create, update, read or delete a type or list of types in the system. 

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
            <td>The numerical ID associated with the type object.</td> 
        </tr>
        <tr>
            <td>description</td>
            <td>Information about the type that is provided by an administrator.</td> 
        </tr>
        <tr>
            <td>title</td>
            <td>The name of the type.</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/type`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_typeAPI.md#POST),
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_typeAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_typeAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_typeAPI.md#DELETE)

Each method will be explained in the following sections:

###<a name=POST>POST</a>
Use this call to **create** a type in the system.

#####Request
######URL
`https://localhost:8443/marketplace/api/type`

######Heading
    Accept = application/JSON
    Content-Type = application/JSON

######Body

    {  
        "title":"Title",
        "description":"This is a description"
    }

#####Response Code:
201

#####Response

    {  
        "id":65,
        "description":"This is a description",
        "title":"Title",
        "_links":{  
            "self":{  
                "href":"https://localhost:8443/marketplace/api/type/65"
            }
        }
    }

#####Requirements
none





###<a name=PUT>PUT</a>###
Use this call to **update** a type in the system.
#####Request
######URL
`https://localhost:8443/marketplace/api/type/{id}`

######Heading
    Accept = application/JSON
    Content-Type = application/JSON

######Body
    {  
        "id":1,
        "title":"Widget",
        "description":"This is the widget description."
    }

#####Response Code:
200

#####Response
    
    {  
        "id":1,
        "description":"This is the widget description.",
        "title":"Widget",
        "_links":{  
            "self":{  
                "href":"https://localhost:8443/marketplace/api/type/1"
            }
        }
    }

#####Requirements
none



###<a name=GET>GET</a>###
Use this call to **read or view** a type or all the types in the system.
#####Request
If you want to see a list of all the types in the system, enter:
`https://localhost:8443/marketplace/api/type/`

However, to view metadata about only one type, enter:
`https://localhost:8443/marketplace/api/type/{id}`
 
Marketplace (i.e., Center) returns the representation of the type that matches the{id}, as shown in the Response for one type id. 

#####Response Code:
200

#####Response for one type id

    {  
        "id":2,
        "description":"A web app",
        "title":"Web Application",
        "_links":{  
            "self":{  
                "href":"https://localhost:8443/marketplace/api/type/2"
            }
        }
    }

#####Requirements
none
#####Optional Parameters
If you want to limit the responses, for example, only return 5, use Optional Parameters which are included in the code as `@QueryParam`:

**offset**--an integer offset 
Example: `https://localhost:8443/marketplace/api/type?offset=5`

**max**--maximum number of type ids your call will return
Example: `https://localhost:8443/marketplace/api/type?max=5`



###<a name=DELETE>DELETE</a>###
Use this call to remove a type from the system.
#####Requirements
`https://localhost:8443/marketplace/api/type/{id}`

#####Response Code:
204

#####Response
no content    
       
#####Requirements
none





###Possible Errors

This table lists common errors. Other errors may occur but these are the most likely:
<table style="width:100%">
    <thead>
        <tr>    
            <td><b>Error Code</b></td>
            <td><b>Error</b></td>
            <td><b>Troubleshooting</b></td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>400
            <td>Type cannot be deleted.
            <td>See if it is associated with a listing. If any listing is assigned to the type, you cannot delete that type.</td> 
        </tr>
        <tr>
            <td>403
            <td>User cannot create, edit, delete a type.
            <td>Only administrators can create, edit, delete types.</td> 
        </tr>  
        <tr>
            <td>400
            <td>Type cannot be created or updated.
            <td>The type must include all required fields.</td> 
        </tr>
        <tr>
            <td>400
            <td>Type must have a unique name.</td>
            <td>If the type name is not unique, a validation error occurs when you try to save.</td> 
        </tr>
    </tbody>
</table> 



