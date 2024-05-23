package util;

import services.GraphServiceImpl;
import services.GraphServices;

public class GraphServiceFactory {
    public static GraphServices createGraphService(int maxVerts) {
        return  new GraphServiceImpl(maxVerts);
    }
}
