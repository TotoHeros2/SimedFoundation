// DO NOT EDIT.  Make changes to ${entity.classNameWithOptionalPackage}.java instead.
#if ($entity.superclassPackageName)
package $entity.superclassPackageName;

#end
import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;

import er.extensions.eof.*;
import er.extensions.foundation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.hcuge.simed.foundation.utilities.validator.*;

@SuppressWarnings("all")
public abstract class ${entity.prefixClassNameWithoutPackage} extends #if ($entity.parentClassNameSet)${entity.parentClassName}#elseif ($entity.partialEntitySet)er.extensions.partials.ERXPartial<${entity.partialEntity.className}>#elseif ($entity.parentSet)${entity.parent.classNameWithDefault}#elseif ($EOGenericRecord)${EOGenericRecord}#elseif ($superClassName)${superClassName}#else ERXGenericRecord#end {
#if ($entity.partialEntitySet)
  public static final String ENTITY_NAME = "$entity.partialEntity.name";
#else
  public static final String ENTITY_NAME = "$entity.name";
#end

  // Attribute Keys
#foreach ($attribute in $entity.sortedClassAttributes)
#if (!$attribute.inherited)
  public static final ERXKey<$attribute.javaClassName> ${attribute.uppercaseUnderscoreName} = new ERXKey<$attribute.javaClassName>("$attribute.name");
#end
#end

  // Relationship Keys
#foreach ($relationship in $entity.sortedClassRelationships)
#if (!$relationship.inherited)
#if ($relationship.userInfo.OptionalToOne)
	//opt2one
  public static final ERXKey<$relationship.actualDestination.classNameWithDefault> ${relationship.userInfo.SingularNameCapitalized} = new ERXKey<$relationship.actualDestination.classNameWithDefault>("$relationship.userInfo.SingularName");
  public static final ERXKey<$relationship.actualDestination.classNameWithDefault> ${relationship.uppercaseUnderscoreName} = new ERXKey<$relationship.actualDestination.classNameWithDefault>("$relationship.name");
#else
  public static final ERXKey<$relationship.actualDestination.classNameWithDefault> ${relationship.uppercaseUnderscoreName} = new ERXKey<$relationship.actualDestination.classNameWithDefault>("$relationship.name");
#end
#end
#end

  // Attributes
#foreach ($attribute in $entity.sortedClassAttributes)
#if (!$attribute.inherited)
  public static final String ${attribute.uppercaseUnderscoreName}_KEY = ${attribute.uppercaseUnderscoreName}.key();
#end
#end

  // Relationships
#foreach ($relationship in $entity.sortedClassRelationships)
#if (!$relationship.inherited)
#if ($relationship.userInfo.OptionalToOne)
	//opt2one
  public static final String ${relationship.userInfo.SingularNameCapitalized}_KEY = ${relationship.userInfo.SingularNameCapitalized}.key();
  public static final String ${relationship.uppercaseUnderscoreName}_KEY = ${relationship.uppercaseUnderscoreName}.key();
#else
  public static final String ${relationship.uppercaseUnderscoreName}_KEY = ${relationship.uppercaseUnderscoreName}.key();
#end
#end
#end

  private static final Logger log = LoggerFactory.getLogger(${entity.prefixClassNameWithoutPackage}.class);

#if (!$entity.partialEntitySet)
#if ($entity.parentSet)
  @Override
#end
  public $entity.classNameWithOptionalPackage localInstanceIn(EOEditingContext editingContext) {
    $entity.classNameWithOptionalPackage localInstance = ($entity.classNameWithOptionalPackage)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

#end
#foreach ($attribute in $entity.sortedClassAttributes)
/*
<h2>documentation for: $attribute.name</h2> 
<pre>
-inherited: $attribute.inherited
-userInfo: $attribute.userInfo
-javaClassName: $attribute.javaClassName
attribute.capitalizedName = $attribute.capitalizedName
attribute.uppercaseUnderscoreName = $attribute.uppercaseUnderscoreName
attribute.fullyQualifiedName = $attribute.fullyQualifiedName
**CLASS
attribute.javaClassName = $attribute.javaClassName
attribute.valueClassName = $attribute.valueClassName
attribute.valueType = $attribute.valueType
**PROPERTIES
attribute.columnName = $attribute.columnName
attribute.definition = $attribute.definition
attribute.definitionPath = $attribute.definitionPath
attribute.allowsNull = $attribute.allowsNull
attribute.usedForLocking = $attribute.usedForLocking
attribute.prototype = $attribute.prototype
attribute.externalType = $attribute.externalType
attribute.precision = $attribute.precision
attribute.scale = $attribute.scale
attribute.width = $attribute.width
attribute.serverTimeZone = $attribute.serverTimeZone
attribute.prototyped  (isPrototyped) = $attribute.prototyped  (isPrototyped)
attribute.flattened  (isFlattened) = $attribute.flattened  (isFlattened)
attribute.inherited  (isInherited) = $attribute.inherited  (isInherited)
attribute.readOnly  (isReadOnly) = $attribute.readOnly  (isReadOnly)
attribute.indexed  (isIndexed) = $attribute.indexed  (isIndexed)
attribute.classProperty  (isClassProperty) = $attribute.classProperty  (isClassProperty)
attribute.clientClassProperty  (isClientClassProperty) = $attribute.clientClassProperty  (isClientClassProperty)
attribute.commonClassProperty  (isCommonClassProperty) = $attribute.commonClassProperty  (isCommonClassProperty)
attribute.primaryKey  (isPrimaryKey) = $attribute.primaryKey  (isPrimaryKey)
attribute.adaptorValueConversionMethodName = $attribute.adaptorValueConversionMethodName
attribute.factoryMethodArgumentType = $attribute.factoryMethodArgumentType
attribute.valueFactoryMethodName = $attribute.valueFactoryMethodName
attribute.readFormat = $attribute.readFormat
attribute.writeFormat = $attribute.writeFormat
attribute.referenceFailures = $attribute.referenceFailures
attribute.referencingFlattenedAttributes = $attribute.referencingFlattenedAttributes
attribute.referencingRelationships = $attribute.referencingRelationships
**DOCUMENTATION
//attribute.documentation =
 </pre> 
 */
#if (!$attribute.inherited)
#if ($attribute.userInfo.ERXConstantClassName)
  public $attribute.userInfo.ERXConstantClassName ${attribute.name}() {
    Number value = (Number)storedValueForKey(${entity.prefixClassNameWithoutPackage}.${attribute.uppercaseUnderscoreName}_KEY);
    return ($attribute.userInfo.ERXConstantClassName)value;
  }

  public void set${attribute.capitalizedName}($attribute.userInfo.ERXConstantClassName value) {
    log.debug( "updating $attribute.name from {} to {}", ${attribute.name}(), value);
    takeStoredValueForKey(value, ${entity.prefixClassNameWithoutPackage}.${attribute.uppercaseUnderscoreName}_KEY);
  }
#else
#if ($attribute.prototype == "[EOAttribute: javaEnum]")
#if ($attribute.adaptorValueConversionMethodName == "getCode")
  // Custom accessor to facilitate working with ${attribute.javaClassName} in Popup
  public String ${attribute.name}ForPopup() {
    return this.${attribute.name}() == null ? "" : this.${attribute.name}().getCode();
  }

  public void set${attribute.capitalizedName}ForPopup(String value) {
    this.set${attribute.capitalizedName}(${attribute.javaClassName}.fromCode(value));
  }

#end
#end
/**
@return $attribute.javaClassName was $attribute.documentation
*/
  public $attribute.javaClassName ${attribute.name}() {
    return ($attribute.javaClassName) storedValueForKey(${entity.prefixClassNameWithoutPackage}.${attribute.uppercaseUnderscoreName}_KEY);
  }

  public void set${attribute.capitalizedName}($attribute.javaClassName value) {
    log.debug( "updating $attribute.name from {} to {}", ${attribute.name}(), value);
    takeStoredValueForKey(value, ${entity.prefixClassNameWithoutPackage}.${attribute.uppercaseUnderscoreName}_KEY);
  }
#end

#end
#end

#foreach ($relationship in $entity.sortedClassToOneRelationships)
//RELATIONSHIP TO_ONE_SORTED (sortedClassToOneRelationships)
//relationship.name = ${relationship.name}
//relationship.classNameWithDefault = ${relationship.classNameWithDefault}
//relationship.uppercaseUnderscoreName = ${relationship.uppercaseUnderscoreName}
//relationship.capitalizedName = ${relationship.capitalizedName}
//relationship.flattened = ${relationship.flattened}
//relationship.inherited = ${relationship.inherited}
//relationship.mandatory = ${relationship.mandatory}
//relationship.genericRecord = ${relationship.genericRecord}
//relationship.ownsDestination = ${relationship.ownsDestination}
//relationship.propagatesPrimaryKey = ${relationship.propagatesPrimaryKey}
//relationship.userInfo = ${relationship.userInfo}
#foreach($attr in $relationship.attributes)
//attrName for ${relationship.name} is ${attr.name}
#end
//ACTUAL_DESTINATION
//relationship.actualDestination.name = ${relationship.actualDestination.name}
//relationship.actualDestination.classNameWithDefault = ${relationship.actualDestination.classNameWithDefault}
//relationship.actualDestination.uppercaseUnderscoreName = ${relationship.actualDestination.uppercaseUnderscoreName}
//relationship.actualDestination.capitalizedName = ${relationship.actualDestination.capitalizedName}
//relationship.actualDestination.flattened = ${relationship.actualDestination.flattened}
//relationship.actualDestination.inherited = ${relationship.actualDestination.inherited}
//relationship.actualDestination.mandatory = ${relationship.actualDestination.mandatory}
//relationship.actualDestination.genericRecord = ${relationship.actualDestination.genericRecord}
//relationship.actualDestination.ownsDestination = ${relationship.actualDestination.ownsDestination}
//relationship.actualDestination.propagatesPrimaryKey = ${relationship.actualDestination.propagatesPrimaryKey}
//relationship.actualDestination.userInfo = ${relationship.actualDestination.userInfo}
#foreach($attr in $relationship.actualDestination.attributes)
//attrName for ${relationship.actualDestination.name} is ${attr.name}
#end
//INVERSE
//relationship.inverseRelationship.name = ${relationship.inverseRelationship.name}
//relationship.inverseRelationship.classNameWithDefault = ${relationship.inverseRelationship.classNameWithDefault}
//relationship.inverseRelationship.uppercaseUnderscoreName = ${relationship.inverseRelationship.uppercaseUnderscoreName}
//relationship.inverseRelationship.capitalizedName = ${relationship.inverseRelationship.capitalizedName}
//relationship.inverseRelationship.flattened = ${relationship.inverseRelationship.flattened}
//relationship.inverseRelationship.inherited = ${relationship.inverseRelationship.inherited}
//relationship.inverseRelationship.mandatory = ${relationship.inverseRelationship.mandatory}
//relationship.inverseRelationship.genericRecord = ${relationship.inverseRelationship.genericRecord}
//relationship.inverseRelationship.ownsDestination = ${relationship.inverseRelationship.ownsDestination}
//relationship.inverseRelationship.propagatesPrimaryKey = ${relationship.inverseRelationship.propagatesPrimaryKey}
//relationship.inverseRelationship.userInfo = ${relationship.inverseRelationship.userInfo}
#foreach($attr in $relationship.inverseRelationship.attributes)
//attrName for ${relationship.inverseRelationship.name} is ${attr.name}
#end
#if (!$relationship.inherited)
  public $relationship.actualDestination.classNameWithDefault ${relationship.name}() {
    return ($relationship.actualDestination.classNameWithDefault)storedValueForKey(${entity.prefixClassNameWithoutPackage}.${relationship.uppercaseUnderscoreName}_KEY);
  }

  public void set${relationship.capitalizedName}($relationship.actualDestination.classNameWithDefault value) {
    takeStoredValueForKey(value, ${entity.prefixClassNameWithoutPackage}.${relationship.uppercaseUnderscoreName}_KEY);
  }

  public void set${relationship.capitalizedName}Relationship($relationship.actualDestination.classNameWithDefault value) {
    log.debug("updating $relationship.name from {} to {}", ${relationship.name}(), value);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      set${relationship.capitalizedName}(value);
    }
    else if (value == null) {
      $relationship.actualDestination.classNameWithDefault oldValue = ${relationship.name}();
      if (oldValue != null) {
        removeObjectFromBothSidesOfRelationshipWithKey(oldValue, ${entity.prefixClassNameWithoutPackage}.${relationship.uppercaseUnderscoreName}_KEY);
      }
    } else {
      addObjectToBothSidesOfRelationshipWithKey(value, ${entity.prefixClassNameWithoutPackage}.${relationship.uppercaseUnderscoreName}_KEY);
    }
  }

#end
#end
#foreach ($relationship in $entity.sortedClassToManyRelationships)
//RELATIONSHIP sortedClassToManyRelationships
//relationship.name = ${relationship.name}
//relationship.classNameWithDefault = ${relationship.classNameWithDefault}
//relationship.uppercaseUnderscoreName = ${relationship.uppercaseUnderscoreName}
//relationship.capitalizedName = ${relationship.capitalizedName}
//relationship.flattened = ${relationship.flattened}
//relationship.inherited = ${relationship.inherited}
//relationship.mandatory = ${relationship.mandatory}
//relationship.genericRecord = ${relationship.genericRecord}
//relationship.ownsDestination = ${relationship.ownsDestination}
//relationship.propagatesPrimaryKey = ${relationship.propagatesPrimaryKey}
//relationship.userInfo = ${relationship.userInfo}
#foreach($attr in $relationship.attributes)
//attrName for ${relationship.name} is ${attr.name}
#end
//ACTUAL_DESTINATION
//relationship.actualDestination.name = ${relationship.actualDestination.name}
//relationship.actualDestination.classNameWithDefault = ${relationship.actualDestination.classNameWithDefault}
//relationship.actualDestination.uppercaseUnderscoreName = ${relationship.actualDestination.uppercaseUnderscoreName}
//relationship.actualDestination.capitalizedName = ${relationship.actualDestination.capitalizedName}
//relationship.actualDestination.flattened = ${relationship.actualDestination.flattened}
//relationship.actualDestination.inherited = ${relationship.actualDestination.inherited}
//relationship.actualDestination.mandatory = ${relationship.actualDestination.mandatory}
//relationship.actualDestination.genericRecord = ${relationship.actualDestination.genericRecord}
//relationship.actualDestination.ownsDestination = ${relationship.actualDestination.ownsDestination}
//relationship.actualDestination.propagatesPrimaryKey = ${relationship.actualDestination.propagatesPrimaryKey}
//relationship.actualDestination.userInfo = ${relationship.actualDestination.userInfo}
#foreach($attr in $relationship.actualDestination.attributes)
//attrName for ${relationship.actualDestination.name} is ${attr.name}
#end
//INVERSE
//relationship.inverseRelationship.name = ${relationship.inverseRelationship.name}
//relationship.inverseRelationship.classNameWithDefault = ${relationship.inverseRelationship.classNameWithDefault}
//relationship.inverseRelationship.uppercaseUnderscoreName = ${relationship.inverseRelationship.uppercaseUnderscoreName}
//relationship.inverseRelationship.capitalizedName = ${relationship.inverseRelationship.capitalizedName}
//relationship.inverseRelationship.flattened = ${relationship.inverseRelationship.flattened}
//relationship.inverseRelationship.inherited = ${relationship.inverseRelationship.inherited}
//relationship.inverseRelationship.mandatory = ${relationship.inverseRelationship.mandatory}
//relationship.inverseRelationship.genericRecord = ${relationship.inverseRelationship.genericRecord}
//relationship.inverseRelationship.ownsDestination = ${relationship.inverseRelationship.ownsDestination}
//relationship.inverseRelationship.propagatesPrimaryKey = ${relationship.inverseRelationship.propagatesPrimaryKey}
//relationship.inverseRelationship.userInfo = ${relationship.inverseRelationship.userInfo}
#foreach($attr in $relationship.inverseRelationship.attributes)
//attrName for ${relationship.inverseRelationship.name} is ${attr.name}
#end
#if (!$relationship.inherited)
#if ($relationship.userInfo.OptionalToOne) 
	#set($visibility = "private") 
	#set($keyName = ${relationship.uppercaseUnderscoreName})
#else 
	#set($visibility = "public") 
	#set($keyName = ${relationship.uppercaseUnderscoreName})
#end 
//optionalToOne [${relationship.name}] will be ${visibility}
#if ($relationship.userInfo.OptionalToOne)
  public ${relationship.actualDestination.classNameWithDefault} ${relationship.userInfo.SingularName}(){
    if(${relationship.name}().count() == 1){
      return ${relationship.name}().objectAtIndex(0);
    } else if(${relationship.name}().count() > 1){
      log.error("${relationship.name}() has ${relationship.name}().count() objects");
      return null;
    }
    return null;
  }
  
  public void set${relationship.actualDestination.classNameWithoutPackage}(${relationship.actualDestination.classNameWithDefault} new${relationship.actualDestination.classNameWithoutPackage}) {
    deleteAll${relationship.capitalizedName}Relationships();
    if(new${relationship.actualDestination.classNameWithoutPackage} !=null){
    	addTo${relationship.capitalizedName}Relationship(new${relationship.actualDestination.classNameWithoutPackage});
    }
  }
  
#end
	//NOT inherited
  ${visibility} NSArray<${relationship.actualDestination.classNameWithDefault}> ${relationship.name}() {
	  //R01
    return (NSArray<${relationship.actualDestination.classNameWithDefault}>)storedValueForKey(${entity.prefixClassNameWithoutPackage}.${keyName}_KEY);
  }

#if (!$relationship.inverseRelationship || $relationship.flattened || !$relationship.inverseRelationship.classProperty)
  ${visibility} NSArray<${relationship.actualDestination.classNameWithDefault}> ${relationship.name}(EOQualifier qualifier) {
    //R02
    return ${relationship.name}(qualifier, null);
  }
#else
	${visibility} NSArray<${relationship.actualDestination.classNameWithDefault}> ${relationship.name}(EOQualifier qualifier) {
	  //R03
    return ${relationship.name}(qualifier, null, false);
  }

	${visibility} NSArray<${relationship.actualDestination.classNameWithDefault}> ${relationship.name}(EOQualifier qualifier, boolean fetch) {
	  //R04
    return ${relationship.name}(qualifier, null, fetch);
  }
#end

  ${visibility} NSArray<${relationship.actualDestination.classNameWithDefault}> ${relationship.name}(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings#if ($relationship.inverseRelationship && !$relationship.flattened && $relationship.inverseRelationship.classProperty), boolean fetch#end) {
	  //R05
    NSArray<${relationship.actualDestination.classNameWithDefault}> results;
#if ($relationship.inverseRelationship && !$relationship.flattened && $relationship.inverseRelationship.classProperty)
    if (fetch) {
      EOQualifier fullQualifier;
#if (${relationship.actualDestination.genericRecord})
      EOQualifier inverseQualifier = ERXQ.equals("${relationship.inverseRelationship.name}", this);
#else
      EOQualifier inverseQualifier = ERXQ.equals(${relationship.actualDestination.classNameWithDefault}.${relationship.inverseRelationship.uppercaseUnderscoreName}_KEY, this);
#end

      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        fullQualifier = ERXQ.and(qualifier, inverseQualifier);
      }

#if (${relationship.actualDestination.genericRecord})
      ERXFetchSpecification<${entity.classNameWithOptionalPackage}> fetchSpec = new ERXFetchSpecification<${entity.classNameWithOptionalPackage}>("${relationship.actualDestination.name}", qualifier, sortOrderings);
      results = (NSArray<${relationship.actualDestination.classNameWithDefault}>)editingContext().objectsWithFetchSpecification(fetchSpec);
#else
      results = ${relationship.actualDestination.classNameWithDefault}.fetch${relationship.actualDestination.pluralName}(editingContext(), fullQualifier, sortOrderings);
#end
    }
    else {
#end
      results = ${relationship.name}();
      if (qualifier != null) {
        results = (NSArray<${relationship.actualDestination.classNameWithDefault}>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<${relationship.actualDestination.classNameWithDefault}>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
#if ($relationship.inverseRelationship && !$relationship.flattened && $relationship.inverseRelationship.classProperty)
    }
#end
    return results;
  }

  ${visibility} void addTo${relationship.capitalizedName}($relationship.actualDestination.classNameWithDefault object) {
	  //R06
    includeObjectIntoPropertyWithKey(object, ${entity.prefixClassNameWithoutPackage}.${keyName}_KEY);
  }

  ${visibility} void removeFrom${relationship.capitalizedName}($relationship.actualDestination.classNameWithDefault object) {
	  //R07
    excludeObjectFromPropertyWithKey(object, ${entity.prefixClassNameWithoutPackage}.${keyName}_KEY);
  }

  ${visibility} void addTo${relationship.capitalizedName}Relationship($relationship.actualDestination.classNameWithDefault object) {
	  //R08
    log.debug("adding {} to ${relationship.name} relationship", object);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      addTo${relationship.capitalizedName}(object);
    }
    else {
      addObjectToBothSidesOfRelationshipWithKey(object, ${entity.prefixClassNameWithoutPackage}.${keyName}_KEY);
    }
  }

  ${visibility} void removeFrom${relationship.capitalizedName}Relationship($relationship.actualDestination.classNameWithDefault object) {
	  //R09
    log.debug("removing {} from ${relationship.name} relationship", object);
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
      removeFrom${relationship.capitalizedName}(object);
    }
    else {
      removeObjectFromBothSidesOfRelationshipWithKey(object, ${entity.prefixClassNameWithoutPackage}.${keyName}_KEY);
    }
  }

  ${visibility} $relationship.actualDestination.classNameWithDefault create${relationship.capitalizedName}Relationship() {
	  //R10
    EOEnterpriseObject eo = EOUtilities.createAndInsertInstance(editingContext(), #if(${relationship.actualDestination.genericRecord})"${relationship.actualDestination.name}"#else ${relationship.actualDestination.classNameWithDefault}.ENTITY_NAME #end);
    addObjectToBothSidesOfRelationshipWithKey(eo, ${entity.prefixClassNameWithoutPackage}.${keyName}_KEY);
    return ($relationship.actualDestination.classNameWithDefault) eo;
  }

  ${visibility} void delete${relationship.capitalizedName}Relationship($relationship.actualDestination.classNameWithDefault object) {
	  //R11
    removeObjectFromBothSidesOfRelationshipWithKey(object, ${entity.prefixClassNameWithoutPackage}.${keyName}_KEY);
#if (!$relationship.ownsDestination)
    editingContext().deleteObject(object);
#end
  }

  ${visibility} void deleteAll${relationship.capitalizedName}Relationships() {
	  //R12
    Enumeration<$relationship.actualDestination.classNameWithDefault> objects = ${relationship.name}().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      delete${relationship.capitalizedName}Relationship(objects.nextElement());
    }
  }

#end
#end

  public #if (!$entity.partialEntitySet)static #end${entity.classNameWithOptionalPackage}#if (!$entity.partialEntitySet) create#else init#end${entity.name}(EOEditingContext editingContext#foreach ($attribute in $entity.sortedClassAttributes)
#if (!$attribute.allowsNull)
#set ($restrictingQualifierKey = 'false')
#foreach ($qualifierKey in $entity.restrictingQualifierKeys)#if ($attribute.name == $qualifierKey)#set ($restrictingQualifierKey = 'true')#end#end
#if ($restrictingQualifierKey == 'false')
#if ($attribute.userInfo.ERXConstantClassName), ${attribute.userInfo.ERXConstantClassName}#else, ${attribute.javaClassName}#end ${attribute.name}
#end
#end
#end
#foreach ($relationship in $entity.sortedClassToOneRelationships)
//RELATIONSHIP TO_ONE_2 sortedClassToOneRelationships createObject for relationship.name {relationship.name}
#if ($relationship.mandatory && !($relationship.ownsDestination && $relationship.propagatesPrimaryKey)), ${relationship.actualDestination.classNameWithDefault} ${relationship.name}#end
#end
) {
    ${entity.classNameWithOptionalPackage} eo = (${entity.classNameWithOptionalPackage})#if ($entity.partialEntitySet)this;#else EOUtilities.createAndInsertInstance(editingContext, ${entity.prefixClassNameWithoutPackage}.ENTITY_NAME);#end

#foreach ($attribute in $entity.sortedClassAttributes)
#if (!$attribute.allowsNull)
#set ($restrictingQualifierKey = 'false')
#foreach ($qualifierKey in $entity.restrictingQualifierKeys)
#if ($attribute.name == $qualifierKey)
#set ($restrictingQualifierKey = 'true')
#end
#end
#if ($restrictingQualifierKey == 'false')
    eo.set${attribute.capitalizedName}(${attribute.name});
#end
#end
#end
#foreach ($relationship in $entity.sortedClassToOneRelationships)
//RELATIONSHIP TO_ONE_3 sortedClassToOneRelationships createObject for relationship.name {relationship.name}
#if ($relationship.mandatory && !($relationship.ownsDestination && $relationship.propagatesPrimaryKey))
    eo.set${relationship.capitalizedName}Relationship(${relationship.name});
#end
#end
    return eo;
  }
#if (!$entity.partialEntitySet)

#if ($entity.parentSet)
  public static ERXFetchSpecification<${entity.classNameWithOptionalPackage}> fetchSpecFor${entity.name}() {
    return new ERXFetchSpecification<${entity.classNameWithOptionalPackage}>(${entity.prefixClassNameWithoutPackage}.ENTITY_NAME, null, null, false, true, null);
  }
#else
  public static ERXFetchSpecification<${entity.classNameWithOptionalPackage}> fetchSpec() {
    return new ERXFetchSpecification<${entity.classNameWithOptionalPackage}>(${entity.prefixClassNameWithoutPackage}.ENTITY_NAME, null, null, false, true, null);
  }
#end

  public static NSArray<${entity.classNameWithOptionalPackage}> fetchAll${entity.pluralName}(EOEditingContext editingContext) {
    return ${entity.prefixClassNameWithoutPackage}.fetchAll${entity.pluralName}(editingContext, null);
  }

  public static NSArray<${entity.classNameWithOptionalPackage}> fetchAll${entity.pluralName}(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return ${entity.prefixClassNameWithoutPackage}.fetch${entity.pluralName}(editingContext, null, sortOrderings);
  }

  public static NSArray<${entity.classNameWithOptionalPackage}> fetch${entity.pluralName}(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<${entity.classNameWithOptionalPackage}> fetchSpec = new ERXFetchSpecification<${entity.classNameWithOptionalPackage}>(${entity.prefixClassNameWithoutPackage}.ENTITY_NAME, qualifier, sortOrderings);
    NSArray<${entity.classNameWithOptionalPackage}> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static ${entity.classNameWithOptionalPackage} fetch${entity.name}(EOEditingContext editingContext, String keyName, Object value) {
    return ${entity.prefixClassNameWithoutPackage}.fetch${entity.name}(editingContext, ERXQ.equals(keyName, value));
  }

  public static ${entity.classNameWithOptionalPackage} fetch${entity.name}(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<${entity.classNameWithOptionalPackage}> eoObjects = ${entity.prefixClassNameWithoutPackage}.fetch${entity.pluralName}(editingContext, qualifier, null);
    ${entity.classNameWithOptionalPackage} eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one ${entity.name} that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ${entity.classNameWithOptionalPackage} fetchRequired${entity.name}(EOEditingContext editingContext, String keyName, Object value) {
    return ${entity.prefixClassNameWithoutPackage}.fetchRequired${entity.name}(editingContext, ERXQ.equals(keyName, value));
  }

  public static ${entity.classNameWithOptionalPackage} fetchRequired${entity.name}(EOEditingContext editingContext, EOQualifier qualifier) {
    ${entity.classNameWithOptionalPackage} eoObject = ${entity.prefixClassNameWithoutPackage}.fetch${entity.name}(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no ${entity.name} that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static ${entity.classNameWithOptionalPackage} localInstanceIn(EOEditingContext editingContext, ${entity.classNameWithOptionalPackage} eo) {
    ${entity.classNameWithOptionalPackage} localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
#end

//FetchSpecification
#foreach ($fetchSpecification in $entity.sortedFetchSpecs)
#if (true || $fetchSpecification.distinctBindings.size() > 0)
  public static NSArray#if ($fetchSpecification.fetchEnterpriseObjects)<${entity.className}>#else<NSDictionary>#end fetch${fetchSpecification.capitalizedName}(EOEditingContext editingContext, NSDictionary<String, Object> bindings) {
    EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("${fetchSpecification.name}", ${entity.prefixClassNameWithoutPackage}.ENTITY_NAME);
    fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
    return (NSArray#if ($fetchSpecification.fetchEnterpriseObjects)<${entity.className}>#else<NSDictionary>#end)editingContext.objectsWithFetchSpecification(fetchSpec);
  }

#end
  public static NSArray#if ($fetchSpecification.fetchEnterpriseObjects)<${entity.className}>#else<NSDictionary>#end fetch${fetchSpecification.capitalizedName}(EOEditingContext editingContext#foreach ($binding in $fetchSpecification.distinctBindings),
  ${binding.attributePath.childClassName} ${binding.name}Binding#end)
  {
    EOFetchSpecification fetchSpec = EOFetchSpecification.fetchSpecificationNamed("${fetchSpecification.name}", ${entity.prefixClassNameWithoutPackage}.ENTITY_NAME);
#if ($fetchSpecification.distinctBindings.size() > 0)
    NSMutableDictionary<String, Object> bindings = new NSMutableDictionary<String, Object>();
#foreach ($binding in $fetchSpecification.distinctBindings)
    bindings.takeValueForKey(${binding.name}Binding, "${binding.name}");
#end
    fetchSpec = fetchSpec.fetchSpecificationWithQualifierBindings(bindings);
#end
    return (NSArray#if ($fetchSpecification.fetchEnterpriseObjects)<${entity.className}>#else<NSDictionary>#end)editingContext.objectsWithFetchSpecification(fetchSpec);
  }

#end

//Custom method for SIMED
#parse("cleanup.vm")

#parse("validateData.vm")

#parse("errorStyle.vm")

#parse("redPoint.vm")

#parse("minMax.vm")

//Base utility 
#parse("constraint.vm")

}
