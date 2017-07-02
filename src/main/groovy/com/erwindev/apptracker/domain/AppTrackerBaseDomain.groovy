package com.erwindev.apptracker.domain

/**
 * Created by erwinalberto on 7/1/17.
 */
class AppTrackerBaseDomain implements Serializable{
    Map asMap(){
        Map map = this.class.declaredFields.findAll { !it.synthetic }.collectEntries {
            [ (it.name):this."$it.name" ]
        }

        Map removedNullMap = [:]
        for (domain in map){
            //exluding __cobertura_counters so it doesn't error out during cobertura testing
            if (domain.value && domain.key != '__cobertura_counters'){
                removedNullMap.put(toSnakeCase(domain.key), domain.value)
            }
        }

        removedNullMap
    }

    static String toSnakeCase( String text ) {
        text.replaceAll( /([A-Z])/, /_$1/ ).toLowerCase().replaceAll( /^_/, '' )
    }
}
