package com.domingosuarez.jsonschema

import com.sun.codemodel.JCodeModel
import org.jsonschema2pojo.SchemaMapper
import spock.lang.Specification

/**
 * Created by domix on 13/07/15.
 */
class Poc extends Specification {

  public static final SchemaMapper mapper = new SchemaMapper()
  public static final String BASE_PACKAGE = "com.domingosuarez.jsonschema"
  public static final String OUTPUT_DIR = "./build/"

  def foo() {
    when:
      JCodeModel codeModel = new JCodeModel()

      def sampleSchema = './src/main/resources/schemas/sample.json'
      //def addressSchema = './src/main/resources/schemas/address.json'
      def person = './src/main/resources/schemas/person.json'
      def complexPerson = './src/main/resources/schemas/complexperson.json'

      createSourceFromLocalSchema(codeModel, sampleSchema, 'Sample')
      //createSourceFromLocalSchema(codeModel, addressSchema, 'MyAddress')
      createSourceFromLocalSchema(codeModel, person, 'Person')
      createSourceFromLocalSchema(codeModel, complexPerson, 'ComplexPerson')
    then:
      true
  }

  void createSourceFromLocalSchema(JCodeModel codeModel, String source, String className) {
    URL sourceUri = new File(source).toURI().toURL()

    mapper.generate(codeModel, className, BASE_PACKAGE, sourceUri)

    codeModel.build(new File(OUTPUT_DIR))
  }

}
