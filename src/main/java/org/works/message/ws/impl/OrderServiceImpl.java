/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.works.message.ws.impl;

import javax.jws.WebService;

import org.works.message.bean.Book;
import org.works.message.bean.Order;
import org.works.message.ws.OrderService;

/**
 * Service for placing a book order.
 * @see OrderService
 */
@WebService(serviceName="OrderService", endpointInterface="org.mule.example.bookstore.OrderService")
public class OrderServiceImpl implements OrderService
{
    public Order orderBook(Book book, int quantity, String address, String email)
    {
        System.out.println("Order has been placed for book: " + book.getTitle());
        return new Order(book, quantity, address, email);
    }
}
