package com.erwindev.apptracker.dao

import com.erwindev.apptracker.domain.Address
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

/**
 * Created by erwinalberto on 6/21/17.
 */
@Component
@Slf4j
class AddressDao extends AppTrackerBaseDao{

    Address getAddress(addressId, addressTypeId){
        Sql sql = new Sql(dataSource)
        def params = [address_id: addressId, address_type_id: addressTypeId]
        Address.newInstance(sql.firstRow(params, GET_ADDRESS))
    }

    String getTableName(){
        "address"
    }

    private final String GET_ADDRESS =
            """select * from ${getTableName()}
               where address_id = :address_id
               and address_type_id = :address_type_id"""
}
