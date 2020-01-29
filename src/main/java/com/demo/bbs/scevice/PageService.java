package com.demo.bbs.scevice;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


public interface PageService {


    void paging(Integer pagel, Model model, Model mode2 );

}
