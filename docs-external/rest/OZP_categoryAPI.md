#API Object: marketplace.category

##Definition 
Use the `/category` API to create, update, read or delete a category or list of categories in the system. 

##Resource Information
The following properties appear in the Category JSON:

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
            <td>The numerical ID associated with the category object.</td> 
        </tr>
        <tr>
            <td>description</td>
            <td>Information about the category that is provided by an administrator.</td> 
        </tr>
        <tr>
            <td>title</td>
            <td>The name of the category.</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/category`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_categoryAPI.md#POST),
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_categoryAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_categoryAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_categoryAPI.md#DELETE)
<br>
Each method will be explained in the following sections:

###<a name=POST>POST</a>
Use this call to **create** a category in the system.

#####Request
######URL
`https://localhost:8443/marketplace/api/category`
######Headers
    Accept = application/JSON
    Content-Type = application/JSON
######Body
    {  
        "title":"News",
        "description":"This is the News category."
    }

#####Response Code:
201

#####Response

    {  
        "id":65,
        "description":"This is the News category.",
        "title":"News",
        "_links":{  
            "self":{  
                "href":"https://localhost:8443/marketplace/api/category/65"
            }
        }
    }

#####Requirements
none
<br>




###<a name=PUT>PUT</a>###
Use this call to **update** a category in the system.
#####Request
######URL
`https://localhost:8443/marketplace/api/category/{id}`
######Headers
    Accept = application/JSON
    Content-Type = application/JSON
######Body
    {  
        "id":65,
        "title":"${News}",
        "description":"This is the News category."
    }

#####Response Code:
200

#####Response
    
    {  
        "id":65,
        "description":"This is the News category.",
        "title":"News",
        "_links":{  
            "self":{  
                "href":"https://localhost:8443/marketplace/api/category/65"
            }
        }
    }

#####Requirements
none
<br>
<br>


###<a name=GET>GET</a>###
Use this call to **read or view** a category or all the categories in the system.
#####Request
If you want to see a list of all the categories in the system, enter:
`https://localhost:8443/marketplace/api/category/`

However, to view metadata about only one category, enter:
`https://localhost:8443/marketplace/api/category/{id}`
 
Marketplace (i.e., Center) returns the representation of the category that matches the{id}, as shown in the Response for one category id. 

#####Response Code:
200

#####Response for one category id

    {  
        "id":65,
        "description":"This is the News category.",
        "title":"News",
        "_links":{  
            "self":{  
                "href":"https://localhost:8443/marketplace/api/category/65"
            }
        }
    }

#####Requirements
none
#####Optional Parameters
If you want to limit the responses, for example, only return 5, use Optional Parameters which are included in the code as `@QueryParam`:

**offset**--an integer offset <br>
Example: `https://localhost:8443/marketplace/api/category?offset=5`

**max**--maximum number of category ids your call will return
Example: `https://localhost:8443/marketplace/api/category?max=5`

<br>
<br>
<br>

###<a name=DELETE>DELETE</a>###
Use this call to remove a category from the system.
#####Requirements
`https://localhost:8443/marketplace/api/category/{id}`

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
            <td>Category cannot be deleted.
            <td>See if it is associated with a listing. If any listing is assigned to the category, you cannot delete that category.</td> 
        </tr>
        <tr>
            <td>403
            <td>User cannot create, edit, delete a category.
            <td>Only administrators can create, edit, delete categories.</td> 
        </tr>  
        <tr>
            <td>400
            <td>Categories cannot be created or updated.
            <td>The category must include all required fields.</td> 
        </tr>
        <tr>
            <td>400
            <td>Category must have a unique name.</td>
            <td>If the category name is not unique, a validation error occurs when you try to save.</td> 
        </tr>
    </tbody>
</table> 

