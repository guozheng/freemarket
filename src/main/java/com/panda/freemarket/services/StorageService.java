package com.panda.freemarket.services;

import com.panda.freemarket.dao.BidDAO;
import com.panda.freemarket.dao.BuyerDAO;
import com.panda.freemarket.dao.ProjectDAO;
import com.panda.freemarket.dao.SellerDAO;
import com.panda.freemarket.model.Bid;
import com.panda.freemarket.model.Buyer;
import com.panda.freemarket.model.Project;
import com.panda.freemarket.model.Seller;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import javax.ws.rs.WebApplicationException;
import java.util.List;

/**
 * Created by gzge. All Rights Reserved
 *
 * An abstraction of the storage service.
 */
public abstract class StorageService {

    // for Sellers
    @CreateSqlObject
    abstract SellerDAO sellerDAO();

    public Seller getSellerById(int id) {
        return sellerDAO().getById(id);
    }

    public List<Seller> getSellersByRange(int from, int count) {
        return sellerDAO().getByRange(from, count);
    }

    public void addSeller(String name) {
        sellerDAO().add(name);
    }

    public void updateSeller(Seller seller) {
        sellerDAO().update(seller);
    }

    // for Buyers
    @CreateSqlObject
    abstract BuyerDAO buyerDAO();

    public Buyer getBuyerById(int id) {
        return buyerDAO().getById(id);
    }

    public List<Buyer> getBuyersByRange(int from, int count) {
        return buyerDAO().getByRange(from, count);
    }

    public void addBuyer(String name) {
        buyerDAO().add(name);
    }

    public void updateBuyer(Buyer buyer) {
        buyerDAO().update(buyer);
    }

    // for Bids
    @CreateSqlObject
    abstract BidDAO bidDAO();

    public Bid getBidById(int id) {
        return bidDAO().getById(id);
    }

    public List<Bid> getBidsBySellerId(int sellerId) {
        return bidDAO().getBySellerId(sellerId);
    }

    public List<Bid> getBidsByProjectId(int projectId) {
        return bidDAO().getByProjectId(projectId);
    }

    public void addBid(Bid bid) {
        Project project = projectDAO().getById(bid.getProject_id());
        if (project == null || project.getBid_deadline().getTime() < System.currentTimeMillis()) {
            throw new WebApplicationException("Cannot add bid, project already finished bidding");
        }

        bidDAO().add(bid);
    }

    public void updateBid(Bid bid) {
        bidDAO().update(bid);
    }

    // for projects
    @CreateSqlObject
    abstract ProjectDAO projectDAO();

    public Project getProjectById(int id) {
        return projectDAO().getById(id);
    }

    public List<Project> getProjectByBuyerId(int buyerId) {
        return projectDAO().getByBuyerId(buyerId);
    }

    public List<Project> getOpenProjectsByRange(int from, int count) {
        return projectDAO().getOpenByRange(from, count);
    }

    public List<Project> getClosedProjectsByRange(int from, int count) {
        return projectDAO().getClosedByRange(from, count);
    }

    public void addProject(Project project) {
        projectDAO().add(project);
    }

    public void updateProject(Project project) {
        projectDAO().update(project);
    }

}
