package com.healthcare.babysoft.components;

import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Optional<FuncionarioModel> objFuncionario = funcionarioRepository.findByEmail(authentication.getName());
        FuncionarioModel funcionario = objFuncionario.get();

        Map<String, Object> map = new HashMap<>();
        map.put("nome", funcionario.getNome());
        map.put("cpf", funcionario.getCpf());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(map);

        return accessToken;
    }
}
