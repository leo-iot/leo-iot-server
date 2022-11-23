package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.io.Serializable;

public abstract class DataBaseEntity
        extends PanacheEntityBase
        implements Serializable
{ }
