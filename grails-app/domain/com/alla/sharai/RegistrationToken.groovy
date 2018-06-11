package com.alla.sharai

class RegistrationToken {

    String token
    static belongsTo = [owner: User]

    static constraints = {
        token nullable: false, blank: false, unique: true
    }
}
