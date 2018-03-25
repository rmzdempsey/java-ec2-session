package rmd.com;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            AmazonEC2 ec2 = AmazonEC2ClientBuilder.defaultClient();

            DescribeInstancesRequest req = new DescribeInstancesRequest();

            DescribeInstancesResult res = ec2.describeInstances(req);

            res.getReservations().forEach(r->System.out.println(
                    String.format("reservervationId=%s ownerId=%s",r.getReservationId(), r.getOwnerId())
            ));

//            AWSCredentials creds = getCredentials();
//            System.out.println("creds = " + creds);

//            AmazonEC2 ec2 = getEC2Client(creds);

//            String securityGroupId = createSecurityGroup( ec2, "rmdSGFromSDLTest", "created by awtest project");
//
//            System.out.println("security group = " + securityGroupId );
//
//            assignSSHInboundRuleToSecurityGroup(ec2,securityGroupId);
        }
        catch( Exception ex ){
            ex.printStackTrace();
        }
    }

    private static AWSCredentials getCredentials(){

        // WORKS
        return new ProfileCredentialsProvider("default").getCredentials();

        /*
        WORKS
        return new ProfileCredentialsProvider().getCredentials();
        WORKS
        return DefaultAWSCredentialsProviderChain.getInstance().getCredentials();
        */

    }

    private static AmazonEC2 getEC2Client(AWSCredentials creds ){

        return AmazonEC2ClientBuilder.defaultClient();

        // WORKS
//        return AmazonEC2ClientBuilder
//                .standard()
//                .withCredentials(new AWSStaticCredentialsProvider(creds))
//                .build();
    }

//    private static String createSecurityGroup(AmazonEC2 ec2, final String securityGroupName, String securityGroupDescription ){
//
//        // default is allow all outgoing, deny all incoming
//
//        ec2.describeSecurityGroups()
//                .getSecurityGroups()
//                .stream()
//                .filter(sg->sg.getGroupName().equals(securityGroupName))
//                .count()>1
//
//        DescribeSecurityGroupsRequest dsgr = new DescribeSecurityGroupsRequest()
//                .withGroupNames(securityGroupName);
//        DescribeSecurityGroupsResult responses = ec2.describeSecurityGroups(dsgr);
//        if( responses.getSecurityGroups().isEmpty() ) {
//
//            CreateSecurityGroupRequest csgr = new CreateSecurityGroupRequest()
//                    .withGroupName(securityGroupName)
//                    .withDescription(securityGroupDescription);
//
//            CreateSecurityGroupResult response = ec2.createSecurityGroup(csgr);
//
//            return response.getGroupId();
//        }
//        else{
//            return responses.getSecurityGroups().get(0).getGroupId();
//        }
//    }
//
//    private static boolean checkSecurityGroupExists(AmazonEC2 ec2, final String securityGroupName){
//        boolean b = false;
//
//        try{
//            DescribeSecurityGroupsRequest dsgr = new DescribeSecurityGroupsRequest()
//                    .withGroupNames(securityGroupName);
//            DescribeSecurityGroupsResult responses = ec2.describeSecurityGroups(dsgr);
//            if( responses.getSecurityGroups().stream()
//                    //.filter(sg->sg.getGroupName().equals(securityGroupName)).count() > 0 ){
//                b = true;
//            }
//        }
//        catch( )
//
//        return b;
//    }
//
//    private static void assignSSHInboundRuleToSecurityGroup( AmazonEC2 ec2, String sgId ){
//        IpPermission ipp = createIpPermission("tcp", "0.0.0.0/0", 22 );
//
//        AuthorizeSecurityGroupIngressRequest request = new AuthorizeSecurityGroupIngressRequest()
//                .withGroupId(sgId)
//                .withIpPermissions(ipp);
//
//        AuthorizeSecurityGroupIngressResult response = ec2.authorizeSecurityGroupIngress( request );
//
//    }
//
//    private static IpPermission createIpPermission( String protocol, String ipRange, int port ){
//
//        return new IpPermission()
//                .withIpProtocol(protocol)
//                .withFromPort(port)
//                .withToPort(port)
//                .withIpv4Ranges(
//                        new IpRange().withCidrIp(ipRange)
//                );
//
//    }
}
