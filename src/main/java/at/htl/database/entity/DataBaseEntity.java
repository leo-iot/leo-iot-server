package at.htl.database.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.io.Serializable;

public abstract class DataBaseEntity
        extends PanacheEntityBase
        implements Serializable
{ }
