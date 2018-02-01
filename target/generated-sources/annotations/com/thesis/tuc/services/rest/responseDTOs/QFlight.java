package com.thesis.tuc.services.rest.responseDTOs;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFlight is a Querydsl query type for Flight
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFlight extends EntityPathBase<Flight> {

    private static final long serialVersionUID = -1399983746L;

    public static final QFlight flight = new QFlight("flight");

    public final StringPath fromArrivalTime = createString("fromArrivalTime");

    public final StringPath fromCompany = createString("fromCompany");

    public final StringPath fromDate = createString("fromDate");

    public final StringPath fromDepartureTime = createString("fromDepartureTime");

    public final StringPath fromTown = createString("fromTown");

    public final StringPath id = createString("id");

    public final StringPath latitude_from = createString("latitude_from");

    public final StringPath latitude_to = createString("latitude_to");

    public final StringPath longitude_from = createString("longitude_from");

    public final StringPath longitude_to = createString("longitude_to");

    public final StringPath price = createString("price");

    public final StringPath toCompany = createString("toCompany");

    public final StringPath toDate = createString("toDate");

    public final StringPath toTown = createString("toTown");

    public QFlight(String variable) {
        super(Flight.class, forVariable(variable));
    }

    public QFlight(Path<? extends Flight> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFlight(PathMetadata metadata) {
        super(Flight.class, metadata);
    }

}

