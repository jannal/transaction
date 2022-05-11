package org.jannal.account.facade;

import org.jannal.account.facade.dto.UserRequestDTO;

public interface AccountFacadeService {

    public void registerUser(UserRequestDTO userRequestDTO);
}
