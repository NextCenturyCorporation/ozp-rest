#API Object: marketplace.intent

##Definition 
Use the `/intent` API to create, update, read or delete an intent or list of intents in the system. 

##Resource Information
Intents are the instructions for carrying out a listing's intentions. The following properties appear in the Intent JSON:

<table style="width:100%">
    <thead>
        <tr>
            <td><b>Parameter</b></td>
            <td><b>Description</b></td
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>type</td>
            <td>The expected deliverable of the intent--for example, application/json</td> 
        </tr>
        <tr>
            <td>action</td>
            <td>What is the intent supposed to do--think of actions as verbs like view, share, edit, etc.</td> 
        </tr>
        <tr>
            <td>label <i>(optional)</i></td>
            <td>The name used to identify the intent.</td> 
        </tr>
        <tr>
            <td>iconId <i>(optional)</i></td>
            <td>An icon used to identify the intent--think of the icons that pop up on a smartphone to ask what program you want to use to complete a task (For example: youtube vs. your browser).</td> 
        </tr>
    </tbody>
</table>
 
##Request URL

`https://localhost:8443/marketplace/api/intent`

This placeholder URL will vary depending upon your deployment. Be mindful that `https://localhost:8443/marketplace` is an example "base/context/domain" where your WAR is deployed.  

##Request Methods
[POST](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_intentAPI.md#POST),
[PUT](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_intentAPI.md#PUT), 
[GET](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_intentAPI.md#GET), 
[DELETE](https://github.com/ozone-development/ozp-rest/blob/master/docs-external/rest/OZP_intentAPI.md#DELETE)
<br>
Each method will be explained in the following sections:

###<a name=POST>POST</a>
Use this call to **create** an intent in the system.

#####Request
`https://localhost:8443/marketplace/api/intent`

    {  
        "type":"application/json",
        "action":"Intent",
        "label":null,
        "iconId":null
    }

#####Response Code:
201

#####Response

    {
        "id":5,
        "type":"application/json",
        "action":"Intent",
        "label":null,
        "iconId":null,
        "icon":null,
        "_links":
        {
            "self":
            {
                "href":"https://localhost:8443/marketplace/api/intent/5"
            }
        }
    }

#####Requirements
none
<br>


###<a name=PUT>PUT</a>
Use this call to **update** an intent in the system.
#####Request
`https://localhost:8443/marketplace/api/intent/{id}`

    {
         "type": "application/json",
          "action": "view",
          "label":"Label",
          "iconId":"17cd6841-7232-422f-bc8b-60bee0475099"
    }


#####Response Code:
200

#####Response
    
    {
        "id":32,
        "type":"application/json",
        "action":"view",
        "label":"Label",
        "iconId":"17cd6841-7232-422f-bc8b-60bee0475099",
        "icon":"https://localhost:8443/marketplace/api/image/17cd6841-7232-422f-bc8b-60bee0475099.png",
        "_links":
        {
            "self":
            {
                "href":"https://localhost:8443/marketplace/api/intent/32"
            }
        }
    }

#####Requirements
none
<br>
<br>


###<a name=GET>GET</a>###
Use this call to **read or view** an intent or all the intents in the system.
#####Request
If you want to see a list of all the intents in the system, enter:
`https://localhost:8443/marketplace/api/intent/`

However, to view metadata about only one intent, enter:
`https://localhost:8443/marketplace/api/intent/{id}`
 
Marketplace (i.e., Center) returns the representation of the intent that matches the{id}, as shown in the Response for one intent id. 

#####Response Code:
200

#####Response for one intent id

    {
      "id": 48,
      "type": "application10/json",
      "action": "view10",
      "label": "label10",
      "iconId": null,
      "icon": null,
      "_links": {
        "self": {
          "href": "https://localhost:8443/marketplace/api/intent/48"
        }
      }
    }

#####Requirements
none
#####Optional Parameters
If you want to limit the responses, for example, only return 5, use Optional Parameters which are included in the code as `@QueryParam`:

**offset**--an integer offset <br>
Example: `https://localhost:8443/marketplace/api/intent?offset=5`

**max**--maximum number of intent ids your call will return
Example: `https://localhost:8443/marketplace/api/intent?max=5`

<br>
<br>
<br>

###<a name=DELETE>DELETE</a>
Use this call to remove an intent from the system.
#####Requirements
`https://localhost:8443/marketplace/api/type/{id}`

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
            <td>Intent cannot be deleted.
            <td>See if it is associated with a listing. If any listing is using the intent, you cannot delete that intent.</td> 
        </tr>
        <tr>
            <td>403
            <td>User cannot create, edit, delete an intent.
            <td>Only administrators can create, edit, delete intents.</td> 
        </tr>  
        <tr>
            <td>400
            <td>The intent cannot be created or updated.
            <td>The intent must include all required fields.</td> 
        </tr>
    </tbody>
</table> 



