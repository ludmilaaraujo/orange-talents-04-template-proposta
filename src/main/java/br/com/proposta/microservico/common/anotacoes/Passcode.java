package br.com.proposta.microservico.common.anotacoes;

import org.springframework.security.crypto.encrypt.Encryptors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class Passcode implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String documento) {
        return Encryptors.delux("${passcode.criptografia}", "2830").encrypt(documento);
    }

    @Override
    public String convertToEntityAttribute(String documento) {
        return Encryptors.delux("${passcode.criptografia}", "2830").decrypt(documento);
    }
}
