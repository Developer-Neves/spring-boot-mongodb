package com.jdnevesti.mongodb.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	// decodifica string envida na URL (para espaços e caracteres especiais)
	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8"); // retorna o texto decodificado
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
