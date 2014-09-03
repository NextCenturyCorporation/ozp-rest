package marketplace

import org.codehaus.groovy.grails.web.json.JSONObject
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

@gorm.AuditStamp
class Intent implements Serializable {
    static searchable = {
        root false
        action component: true
        dataType component: true
        send index: 'not_analyzed'
        receive index: 'not_analyzed'
        only = ['send', 'receive', 'dataType', 'action']
    }

    static bindableProperties = ['send', 'receive', 'action', 'dataType']
    static modifiableReferenceProperties = []

    IntentAction action
    IntentDataType dataType
    Boolean send = false
    Boolean receive = false

    static constraints = {
        action nullable: false
        dataType nullable: false
    }

    static belongsTo = [serviceItem: ServiceItem]

    static mapping = {
        cache true
        batchSize 50
    }

    String toString() {
        if (send && receive) {
            "Sends/Receives: $action -> $dataType"
        } else if (send) {
            "Sends: $action -> $dataType"
        } else {
            "Receives: $action -> $dataType"
        }
    }

    String prettyPrint() {
        toString()
    }

    def asJSON() {
        return new JSONObject(
            id: id,
            action: action.asJSONRef(),
            dataType: dataType.asJSONRef(),
            send: send,
            receive: receive
        )
    }

    def bindFromJSON(JSONObject json) {
        [
            "id",
            "action",
            "dataType",
            "send",
            "receive"
        ].each(JS.optStr.curry(json, this))

        [
            "editedDate"
        ].each(JS.optDate.curry(json, this))
    }

    @Override
    int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder()
        builder.append(action?.title)
                .append(dataType?.title)
            .append(send)
            .append(receive)
        def code = builder.toHashCode()
        return code;
    }

    @Override
    boolean equals(Object obj) {

        // Since intents are typically in a lazy loaded collection, the instances could be
        // hibernate proxies, so use the GORM 'instanceOf' method
        Boolean sameType
        try {
            sameType = obj.instanceOf(Intent)
        } catch(MissingMethodException mme) {
            sameType = false
        }

        if (sameType) {
            Intent other = (Intent) obj
            EqualsBuilder builder = new EqualsBuilder()

            builder.append(send, other.send)
                    .append(receive, other.receive)

            if (this.action.title != null && other.action.title != null) {
                builder.append(this.action.title, other.action.title)
            }
            else {
                builder.append(this.action.id, other.action.id)
            }

            if (this.dataType.title != null && other.dataType.title != null) {
                builder.append(this.dataType.title, other.dataType.title)
            }
            else {
                builder.append(this.dataType.id, other.dataType.id)
            }

            return builder.isEquals();
        }
        return false;
    }

}
