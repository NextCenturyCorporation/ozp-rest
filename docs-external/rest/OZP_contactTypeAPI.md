#API Object: marketplace.contactType

##Definition 
Use the `/contactType` API to create, update, read or delete a contact type or list of contact types in the system. 

##Resource Information
The following properties appear in the Contact Type JSON:

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
            <td>The numerical ID associated with the contact type object.</td> 
        </tr>
        <tr>
            <td>required</td>
            <td>A true or false field that validates if the contact type must be included to pass in a listing for approval.</td> 
        </tr>
        <tr>
            <td>title</td>
            <td>The name of the contact type.</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/contactType`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_contactTypeAPI.md#POST),
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_contactTypeAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_contactTypeAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_contactTypeAPI.md#DELETE)
<br>
Each method will be explained in the following sections:

###<a name=POST>POST</a>
Use this call to **create** a contact type in the system.

#####Request
`https://localhost:8443/marketplace/api/contactType`

    {  
        "title":"Technical Contact",
        "required":false
    }


#####Response Code:
201

#####Response

    {  
        "id":5,
        "required":false,
        "title":"Technical Contact",
        "_links":{  
            "self":{  
                "href":"https://localhost:8443/marketplace/api/contactType/5"
            }
        }
    }

#####Requirements
none
<br>




###<a name=PUT>PUT</a>###
Use this call to **update** a contact type in the system.
#####Request
`https://localhost:8443/marketplace/api/contactType/{id}`

    {  
        "id":12,
        "title":"Title",
        "required":false
    }

#####Response Code:
200

#####Response
    
    {  
        "id":12,
        "required":false,
        "title":"Title",
        "_links":{  
            "self":{  
                "href":"https://localhost:8443/marketplace/api/contactType/12"
            }
        }
    }

#####Requirements
none
<br>
<br>


###<a name=GET>GET</a>###
Use this call to **read or view** a contact type or all the contact types in the system.
#####Request
If you want to see a list of all the contact types in the system, enter:
`https://localhost:8443/marketplace/api/contactType/`

However, to view metadata about only one contact type, enter:
`https://localhost:8443/marketplace/api/contactType/{id}`
 
Marketplace returns the representation of the contact type that matches the{id}, as shown in the Response for one contact type id. 

#####Response Code:
200

#####Response for one contact type id
    {  
        "id":30,
        "required":true,
        "title":"ContactType_8",
        "_links":{  
            "self":{  
                "href":"https://localhost:8443/marketplace/api/contactType/30"
            }
        }
    }

#####Requirements
none
#####Optional Parameters
If you want to limit the responses, for example, only return 5, use Optional Parameters which are included in the code as `@QueryParam`:

**offset**--an integer offset <br>
Example: `https://localhost:8443/marketplace/api/contactType?offset=5`

**max**--maximum number of contact type ids your call will return
Example: `https://localhost:8443/marketplace/api/contactType?max=5`

<br>
<br>
<br>

###<a name=DELETE>DELETE</a>###
Use this call to remove a contact type from the system.
#####Requirements
`https://localhost:8443/marketplace/api/contactType/{id}`

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
            <td>403
            <td>User cannot create, edit, delete a contact type.
            <td>Only administrators can create, edit, delete contact types.</td> 
        </tr>  
        <tr>
            <td>400
            <td>Contact types cannot be created or updated.
            <td>The contact type must include all required fields.</td> 
        </tr>
        <tr>
            <td>400
            <td>Contact type must have a unique name.</td>
            <td>If the contact type name is not unique, a validation error occurs when you try to save.</td> 
        </tr>
    </tbody>
</table> 

