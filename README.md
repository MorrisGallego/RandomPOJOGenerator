Random POJO generator for Java (RPG4J)
==============================

With the **Random POJO generator for Java (*RPG4J*)** package you can generate random objects from your Plain Old Java Objects (POJOs) only by annotating them! The only requirement is that your POJOs need to have a default, parameter-less constructor.

Also, you can provide custom generators for the annotations.

And you know what? RPG4J has no dependencies!

## Let's Start
First of all, you need to annotate your POJO fields with one of the annotations provided in the package `es.usc.citius.utils.generator.annotations`.

Then, you can call `es.usc.citius.utils.generator.Generator.forType(YourPojo.class).generate(10)` and you will get a list with 10 instances of `YourPojo`, with the annotated fields populated properly.

##Available Annotations
All annotations support atomic fields and lists. If your field is a list of objects, you can specify the exact length or a maximum and minimum length for it.

Also, you can specify a custom generator in the annotation for any field. 

###`@RandomBoolean()`
###`@RandomDouble()`
###`@RandomFloat()`
###`@RandomInt()`
###`@RandomLong()`
###`@RandomString()`
###`@RandomObject()`

##Creating your custom generators
If you want to provide your custom generator for any of the annotations, you just have to implement the corresponding interface from `es.usc.citius.utils.generator.generators`. 

Currently, you can specify custom generators for `Strings`, `Booleans`, `Doubles`, `Floats`, `Integers` and `Longs`.

Using custom generators you can adapt the generator to your needs, for example to generate custom Strings with email format, or realistic human names.

##Defining custom generators globally
If you want to specify a custom generator globally for all the fields with a concrete type, you can do it by using the `.withDefaultXXXXXGenerator()` method from a `Generator` instance.