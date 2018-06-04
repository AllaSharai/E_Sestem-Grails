package com.alla.sharai

class Document {

    String title
    String text
    boolean approved = false

    static belongsTo = [owner: User]

    static constraints = {
        title nullable: false, blank: false
        text nullable: false, blank: false
        approved nullable:false, default: false
    }
}
