package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * 账户的业务层实现
 *
 * 事务控制应该都是在业务层
 */
public class AccountServiceImpl implements IAccountService {

    //业务层调用持久层
    private IAccountDao accountDao;

    //使用模板对象
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    //spring帮忙注入
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account findAccountById(final Integer accountId) {
        return transactionTemplate.execute(new TransactionCallback<Account>() {
            @Override
            public Account doInTransaction(TransactionStatus transactionStatus) {
                return accountDao.findAccountById(accountId);
            }
        });
    }

    @Override
    public void transfer(final String sourceName, final String targetName, final Float money) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                System.out.println("transfer......");
                //2.1.根据名称查询转出账户
                Account source=accountDao.findAccountByName(sourceName);
                //2.2.根据名称查询转入账户
                Account target=accountDao.findAccountByName(targetName);
                //2.3.转出账户减钱
                source.setMoney(source.getMoney()-money);
                //2.4.转出账户加钱
                target.setMoney(target.getMoney()+money);
                //2.5.更新转出账户
                accountDao.updateAccount(source);

                //int i=1/0;

                //2.6.更新转入账户
                accountDao.updateAccount(target);
                return null;
            }
        });
    }
}
