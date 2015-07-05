package com.purchaseapplication.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.purchaseapplication.model.PurchaseApplication;
import com.purchaseapplication.service.IPurchaseApplicationService;
import com.purchaseapplication.service.PurchaseApplicationServiceImpl;

public class PurchaseApplicationServlet extends HttpServlet {
	PurchaseApplication purchaseApplicationObject = new PurchaseApplication();

	IPurchaseApplicationService purchaseApplicationServiceObject = new PurchaseApplicationServiceImpl();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String productName = request.getParameter("pname");
		String productType = request.getParameter("ptype");
		String productColor = request.getParameter("pcolor");
		String productPrice = request.getParameter("pprice");
		String itemInStock = request.getParameter("pitems");
		String productImage = request.getParameter("pimage");

		purchaseApplicationObject.setProductName(productName);
		purchaseApplicationObject.setProductType(productType);
		purchaseApplicationObject.setProductColor(productColor);
		purchaseApplicationObject.setProductPrice(productPrice);
		purchaseApplicationObject.setItemsInStock(itemInStock);
		purchaseApplicationObject.setImage(productImage);

		boolean addProduct = purchaseApplicationServiceObject
				.addProduct(purchaseApplicationObject);
		if (addProduct) {
			if (!ServletFileUpload.isMultipartContent(request)) {
				out.println("<h2>Nothing to upload</h2>");
				return;
			}
			FileItemFactory itemFactory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(itemFactory);
			try {
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					String contentType = item.getContentType();
					if (!contentType.equals("image/JPG")) {
						out.println("<h4>only JPEG format file support");
						continue;
					}
					File uploadDir = new File("F:\\uploadfiles");
					File file = File.createTempFile("img", ".JPG", uploadDir);
					item.write(file);
				}
			} catch (FileUploadException e) {
				out.println("<h4>upload fail</h4>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.println("<h1>ADDED SUCCESSFULLY</h1>");

			out.println("<h3>List of Products:</h3>");
			List<PurchaseApplication> list = purchaseApplicationServiceObject
					.getList();
			Iterator<PurchaseApplication> iterator = list.iterator();
			out.println("<table border=1 style=width:100%>");
			out.println("<th>");
			out.println("<td>PRODUCT NAME");
			out.println("<td>PRODUCT TYPE");
			out.println("<td>PRODUCT COLOR");
			out.println("<td>PRODUCT PRICE");
			out.println("<td>ITEMS IN STOCK");
			out.println("<td>IMAGE NAME");
			out.println("</th>");
			while (iterator.hasNext()) {
				PurchaseApplication products = iterator.next();

				out.println("<h4><tr><td>" + products.getProductName()
						+ "</td><td>" + products.getProductType() + "</td><td>"
						+ products.getProductColor() + "</td><td>"
						+ products.getProductPrice() + "</td><td>"
						+ products.getItemsInStock() + "</td><td>"
						+ products.getImage() + "</td></tr></h4>");
			}

		} else {
			System.out.println("dfgbnm,");
			out.println("Not ADDED");
			response.sendRedirect("html/purchasing.html");
			out.println("Not ADDED");

		}
	}
}
