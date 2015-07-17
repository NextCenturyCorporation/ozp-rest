#API Object: marketplace.Agency

##Definition 
Use the `/agency` API to create, update, read or delete an agency in the system. 

##Resource Information##
The following properties appear in the Agency JSON: 
<table style="width:100%">
    <thead>
        <tr>
            <td><b>Parameter</b></td>
            <td><b>Description</b></td>
        </tr>
    </thead>
    <tbody>
    <tr>
        <td>id</td>
        <td>The numerical ID associated with the agency object.</td> 
    </tr>
    <tr>
        <td>shortName</td>
        <td>The abbreviation used to identify the agency on listings' short and detailed views.</td> 
    </tr>
    <tr>
        <td>title</td>
        <td>The name of the agency.</td> 
    </tr>
    </tbody>
</table>

 
##Request URL###

`https://localhost:8443/marketplace/api/agency` <br>

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  


##Request Methods
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_agencyAPI.md#POST),
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_agencyAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_agencyAPI.md#GET), [DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_agencyAPI.md#DELETE)
<br>
Each method will be explained in the following sections:

###<a name=POST>POST</a>####
Use this call to **create** an agency in the system.

#####Request
`https://localhost:8443/marketplace/api/agency`

    {  
        "title":"Test Organization",
        "shortName":"TORG"
    }

#####Response Code:
201

#####Response

    {
      "id": 1,
      "shortName": "TORG",
      "title": "Test Organization",
      "_links": {
        "self": {
          "href": "https://localhost:8443/marketplace/api/agency/1"
        }
      }
    }

#####Requirements
none
<br>




###<a name=PUT>PUT</a>
Use this call to **update** an agency in the system.
#####Request
`https://localhost:8443/marketplace/api/agency/{id}`

    {  
        "id":208,
        "title":"Updated Agency",
        "shortName":"sName"
    }

#####Response Code:
200

#####Response
    
    {
      "id": 1,
      "shortName": "TORG",
      "title": "Test Organization",
      "_links": {
        "self": {
          "href": "https://localhost:8443/marketplace/api/agency/1"
        }
      }
    }
#####Requirements
none
<br>
<br>


###<a name=GET>GET</a>
Use this call to **read or view** an agency or all the agencies in the system.
#####Request
If you want to see a list of all the agencies in the system, enter:
`https://localhost:8443/marketplace/api/agency/`

However, to view metadata about only one agency, enter:
`https://localhost:8443/marketplace/api/agency/{id}`
 
Marketplace returns the representation of the agency that matches the{id}, as shown in the Response for one agency id. 

#####Response Code:
200

#####Response for one agency id

    {
       "id":194,
       "shortName":"sName",
       "title":"123456",
       "_links":
       {
          "self":
          {
             "href":"https://localhost:8443/marketplace/api/agency/194"
          }
       }
    }

#####Requirements
none
#####Optional Parameters
If you want to limit the responses, for example, only return 5, you can do so using Optional Parameters which are included in the code as `@QueryParam`:

**offset**--an integer offset <br> 
Example: `https://localhost:8443/marketplace/api/agency?offset=5`

**max**--maximum number of agency ids your call will return
Example: `https://localhost:8443/marketplace/api/agency?max=5`

<br>
<br>
<br>

###<a name=DELETE>DELETE</a>
Use this call to remove an agency from the system.
#####Requirements
`https://localhost:8443/marketplace/api/agency/{id}`

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
            <td>400
            <td>Agency cannot be deleted.
            <td>See if it is associated with a listing. If any listing is assigned to an agency, you cannot delete that agency.</td> 
        </tr>
        <tr>
            <td>403
            <td>User cannot create, edit, delete an agency.
            <td>Only administrators can create, edit, delete agencies.</td> 
        </tr>  
        <tr>
            <td>400
            <td>Agency cannot be created or updated.
            <td>The agency must include all required fields.</td> 
        </tr>
        <tr>
            <td>400
            <td>Agency must have a unique name.</td>
            <td>If the agency name is not unique, a validation error occurs when you try to save.</td> 
        </tr>
    </tbody>
</table> 

